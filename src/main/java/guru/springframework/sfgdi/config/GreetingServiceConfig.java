package guru.springframework.sfgdi.config;

import guru.springframework.sfgdi.services.ConstructorGreetingService;
import guru.springframework.sfgdi.services.PropertyInjectedGreetingService;
import guru.springframework.sfgdi.services.SetterInjectedGreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
