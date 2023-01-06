package com.example.projectlibrary.Setting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HafsizlikSozlamalari extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        String[] resources = new String[]{
//               "/assets/css/ab.css","/assets/vendor/aos/aos.css","/assets/vendor/bootstrap/css/bootstrap.min.css","/assets/vendor/bootstrap-icons/**","/assets/vendor/boxicons/css/boxicons.min.css","/assets/vendor/glightbox/css/glightbox.min.css","/assets/vendor/swiper/swiper-bundle.min.css","/assets/vendor/purecounter/purecounter_vanilla.js","/assets/vendor/aos/aos.js","/assets/vendor/bootstrap/js/bootstrap.bundle.min.js","/assets/vendor/glightbox/js/glightbox.min.js","/assets/vendor/isotope-layout/isotope.pkgd.min.js","/assets/vendor/swiper/swiper-bundle.min.js","/assets/vendor/waypoints/noframework.waypoints.js","/assets/vendor/php-email-form/validate.js"
//        };
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/home","/abonement-va-foydalanuvchilarga-xizmat-korsatish-xizmati").permitAll()
                .antMatchers("/addbook/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("assets/vendor/purecounter/purecounter_vanilla.js","assets/vendor/aos/aos.js","assets/vendor/bootstrap/js/bootstrap.bundle.min.js","assets/vendor/glightbox/js/glightbox.min.js","assets/vendor/isotope-layout/isotope.pkgd.min.js","assets/vendor/swiper/swiper-bundle.min.js","assets/vendor/waypoints/noframework.waypoints.js","assets/vendor/php-email-form/validate.js","/assets/js/main.js").permitAll()
                .antMatchers("assets/vendor/aos/aos.css","assets/vendor/bootstrap/css/bootstrap.min.css","assets/vendor/bootstrap-icons/bootstrap-icons.css","assets/vendor/boxicons/css/boxicons.min.css","assets/vendor/glightbox/css/glightbox.min.css","assets/vendor/swiper/swiper-bundle.min.css").permitAll()
                .antMatchers("/assets/css/ab.css","/assets/css/style2.css").permitAll()
                .antMatchers("/bolim/img/**","/image/display/**","/image/ijara/**","/file/dowlond/**","/dowlond/**","/ijara/**","/kirish/**","/contact-us-panel/**","/about-uz-panel/**","/addNew","/image/new/**","/image/about/**","/ijara/img/**","/qr/img/**").permitAll()
                .antMatchers("/abonement-va-foydalanuvchilarga-xizmat-korsatish-xizmati","/axborot-bibliografiya-xizmati","/fondlarni-toldirish-saqlash-ishlov-berish-xizmati","/axborot-kommunikatsiya-texnalogiyalari-va-raqamlashtirish-xizmati").permitAll()
                .anyRequest().authenticated();
    }

}
