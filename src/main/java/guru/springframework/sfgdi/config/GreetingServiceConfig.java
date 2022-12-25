package guru.springframework.sfgdi.config;

import guru.springframework.sfgdi.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Configuration
public class GreetingServiceConfig {
    //cara ini biasa dipakai kalau class yang akan dipakai itu bukan punya kita
    //sehingga kita ga bisa asal ubah. tapi ini cara jadul banget
    //jadi, ceritanya itu karena class tersebut tidak terdaftar sebagai Stereotype Component,
    //maka kita perlu inisiate class tersebut ke bean biar terdaftar di bean
    //bean yang terdaftar pun menggunakan lower case dari nama class yang terdaftar seperti constructorGreetingService
    //sebenernya bisa2 aja nama bean ga sama dengan nama class, tapi sebaiknya tetap disamakan saja
    @Bean
    ConstructorGreetingService constructorGreetingService(){
        return new ConstructorGreetingService();
    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService(){
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService(){
        return new SetterInjectedGreetingService();
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }

    //ini di set bean nya sebagai i18nService karena sebelumnya ini di set sebagai @Service("i18nService")
    @Profile("EN")
    @Bean
    I18nEnglishGreetingService i18nService(){
        return new I18nEnglishGreetingService();
    }

    //dibawah ini, nama bean awalnya i18NSpanishService di override menjadi i18nService.
    //kenapa ga langsung I18NSpanishService i18nService(){ ? karena i18nService sudah dipakai sebelumnya sama English
    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18NSpanishService i18NSpanishService(){
        return new I18NSpanishService();
    }
}
