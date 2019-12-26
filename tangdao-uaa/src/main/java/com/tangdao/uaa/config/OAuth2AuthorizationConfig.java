/**
 *
 */
package com.tangdao.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.tangdao.uaa.security.service.PasswordEncoderService;

/**
 *  认证配置
 * @author Ryan Ru(ruyangit@gmail.com)
 */

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
	private TokenStore tokenStore = new InMemoryTokenStore();

	@Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoderService passwordEncoderService;
	
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // TODO persist clients details
		String defaultPwd = "66bb0152d2f77e77319cae1da426797163ed747605b0485f099a55f9";
        // @formatter:off
        clients.inMemory()
                .withClient("browser")
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("ui")
                .and()
                .withClient("system-service")
                .secret(defaultPwd)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("server")
                .and();
        // @formatter:on
    }
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(passwordEncoderService);
    }
}
