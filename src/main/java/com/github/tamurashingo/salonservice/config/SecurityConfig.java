package com.github.tamurashingo.salonservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静的リソースに対するアクセスはセキュリティ設定を無視する
        web.ignoring()
                .antMatchers(
                        "/*.html",
                        "/css/**",
                        "/images/**"
                )
                .antMatchers("/bootstrap/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // indexは全ユーザアクセス許可
                .antMatchers("/", "/index").permitAll()
                // ユーザ登録画面は全員許可
                .antMatchers("/sign_up").permitAll()
                // それ以外はすべて認証なしのアクセスは不許可
                .anyRequest().authenticated()
                .and()

                // ログイン設定
                .formLogin()
                // 認証処理のパス
                .loginProcessingUrl("/login")
                // ログイン画面のURL
                .loginPage("/login")
                // エラー時のURL
                .failureUrl("/error")
                // ログイン成功時のURL
                .defaultSuccessUrl("/home", false)
                .usernameParameter("login_id")
                .passwordParameter("login_password")
                .permitAll()
                .and()

                // ログアウト設定
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("signout"))
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
                .and()

                .csrf();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
