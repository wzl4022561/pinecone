/**
 * 
 */
package com.tenline.pinecone.platform.web.service.oauth;

import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.cache.Cache;
import javax.cache.Caching;
import javax.cache.CacheConfiguration.Duration;
import javax.cache.CacheConfiguration.ExpiryType;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.support.JdoDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tenline.pinecone.platform.model.Consumer;

/**
 * @author Bill
 *
 */
@Repository
@Transactional
public class OAuthPersistentProvider extends JdoDaoSupport implements OAuthProvider {

	private String realm;
	private Cache<Object, Object> requestTokens;
	private Cache<Object, Object> accessTokens;
    private final static int EXPIRATION_SECONDS = 3600; // 1 hour
	
	/**
	 * 
	 */
    @Autowired
	public OAuthPersistentProvider(PersistenceManagerFactory persistenceManagerFactory) {
		// TODO Auto-generated constructor stub
		realm = "default";
		setPersistenceManagerFactory(persistenceManagerFactory);
		requestTokens = Caching.getCacheManager().createCacheBuilder("requestTokens")
        .build();
		accessTokens = Caching.getCacheManager().createCacheBuilder("accessTokens")
        .setExpiry(ExpiryType.MODIFIED, new Duration(TimeUnit.SECONDS, EXPIRATION_SECONDS))
        .build();
	}

	@Override
	public Consumer registerConsumer(String consumerKey,
			String displayName, String connectURI) throws OAuthException {
		// TODO Auto-generated method stub			
		do{
			consumerKey = makeRandomString();
	    } while(findConsumer(consumerKey) != null);
        return getJdoTemplate().makePersistent(new Consumer(consumerKey, makeRandomString(), displayName, connectURI));
	}

	@Override
	public void registerConsumerScopes(String consumerKey, String[] scopes)
			throws OAuthException {
		// TODO Auto-generated method stub
		Consumer consumer = findConsumer(consumerKey);
		consumer.setScopes(scopes);
	}
	
	@SuppressWarnings("unchecked")
	private Consumer findConsumer(String consumerKey) throws OAuthException {
        String queryString = "select from " + Consumer.class.getName();
        queryString += " where key == '" + consumerKey + "'";
		Collection<Consumer> consumers = getJdoTemplate().find(queryString);
		Consumer consumer = null;
		if (!consumers.isEmpty()) {
            consumer = (Consumer) consumers.toArray()[0];
        }
		return consumer;
	}

	@Override
	public void registerConsumerPermissions(String consumerKey,
            String[] permissions) throws OAuthException {
        // TODO Auto-generated method stub
        
    }

	@Override
	public String getRealm() {
		// TODO Auto-generated method stub
		return realm;
	}

	@Override
	public Consumer getConsumer(String consumerKey) throws OAuthException {
		// TODO Auto-generated method stub
		return findConsumer(consumerKey);
	}

	@Override
	public OAuthRequestToken getRequestToken(String consumerKey,
			String requestToken) throws OAuthException {
		// TODO Auto-generated method stub
		OAuthRequestToken token = getRequestToken(requestToken);
	    if (consumerKey != null && !token.getConsumer().getKey().equals(consumerKey)) {
	        throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "No such consumer key "+consumerKey);
	    }
		return token;
	}
	
	private OAuthRequestToken getRequestToken(String requestToken) throws OAuthException {
		OAuthRequestToken token = (OAuthRequestToken) requestTokens.get(requestToken);
		if (token == null) {
			throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "No such request token " + requestToken);
		}
		return token;
	}

	@Override
	public OAuthToken getAccessToken(String consumerKey, String accessToken)
			throws OAuthException {
		// TODO Auto-generated method stub
		return doGetAccessToken(consumerKey, accessToken);
	}
	
	@SuppressWarnings("unused")
	private OAuthToken doGetAccessToken(String consumerKey, String accessKey) throws OAuthException{
        // get is atomic
        OAuthToken ret = (OAuthToken) accessTokens.get(accessKey);
        if (!ret.getConsumer().getKey().equals(consumerKey)) {
            throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "Consumer is invalid");
        }
        if(ret == null)
            throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "No such access key "+accessKey);
        return ret;
    }

	@Override
	public OAuthToken makeRequestToken(String consumerKey, String callback,
			String[] scopes, String[] permissions) throws OAuthException {
		// TODO Auto-generated method stub
		OAuthRequestToken token = doMakeRequestToken(consumerKey, callback, scopes, permissions);
		requestTokens.put(token.getToken(), token);
		return token;
	}
	
	private String makeRandomString(){
		return UUID.randomUUID().toString();
	}
	
	private OAuthRequestToken doMakeRequestToken(String consumerKey, String callback, 
			String[] scopes, String[] permissions) throws OAuthException {
		Consumer consumer = findConsumer(consumerKey);
		String newToken;
		do{
			newToken = makeRandomString();
	    }while(requestTokens.containsKey(newToken));
	    OAuthRequestToken token = new OAuthRequestToken(newToken, makeRandomString(), callback, 
	    		scopes, permissions, -1, consumer);
	    requestTokens.put(token.getToken(), token);
	    return token;
	}

	@Override
	public OAuthToken makeAccessToken(String consumerKey, String requestToken,
			String verifier) throws OAuthException {
		// TODO Auto-generated method stub
		OAuthRequestToken token = verifyAndRemoveRequestToken(consumerKey, requestToken, verifier);
		return doMakeAccessTokens(token);
	}
	
	private OAuthToken doMakeAccessTokens(OAuthRequestToken requestToken) throws OAuthException {
        String newToken;
        do{
            newToken = makeRandomString();
        }while(accessTokens.containsKey(newToken));
        OAuthToken token = new OAuthToken(newToken, makeRandomString(), 
                                          requestToken.getScopes(), requestToken.getPermissions(),
                                          -1, requestToken.getConsumer());
        accessTokens.put(token.getToken(), token);
        return token;
	}
	
	private void checkCustomerKey(OAuthToken token, String customerKey) throws OAuthException {
	    if (customerKey != null && !customerKey.equals(token.getConsumer().getKey())) {
            throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "Invalid customer key");
        }
	}
	
	private OAuthRequestToken verifyAndRemoveRequestToken(String customerKey, String requestToken, String verifier) throws OAuthException {
        OAuthRequestToken request = getRequestToken(requestToken);
        checkCustomerKey(request, customerKey);
        // check the verifier, which is only set when the request token was accepted
        if(verifier == null || !verifier.equals(request.getVerifier()))
            throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "Invalid verifier code for token "+requestToken);
        // then let's go through and exchange this for an access token
        OAuthRequestToken token = (OAuthRequestToken) requestTokens.get(requestToken);
        requestTokens.remove(requestToken);
        return token;
    }

	@Override
	public String authoriseRequestToken(String consumerKey, String requestToken)
			throws OAuthException {
		// TODO Auto-generated method stub
		String verifier = makeRandomString();
		OAuthRequestToken token = doGetRequestToken(consumerKey, requestToken);
		token.setVerifier(verifier);
		requestTokens.put(token.getToken(), token);
		return verifier;
	}
	
	private OAuthRequestToken doGetRequestToken(String customerKey, String requestKey) throws OAuthException{
        // get is atomic
        OAuthRequestToken ret = (OAuthRequestToken) requestTokens.get(requestKey);
        checkCustomerKey(ret, customerKey);
        if(ret == null)
            throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "No such request key "+requestKey);
        return ret;
    }

	@Override
	public void checkTimestamp(OAuthToken token, long timestamp)
			throws OAuthException {
		// TODO Auto-generated method stub
		if(token.getTimestamp() > timestamp)
            throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "Invalid timestamp "+timestamp);
	}

	@Override
	public Set<String> convertPermissionsToRoles(String[] permissions) {
        // TODO Auto-generated method stub
        return null;
    }

}
