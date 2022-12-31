package guru.springframework.sfgdi.config;

import com.springframework.pets.PetService;
import com.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

//@PropertySource("classpath:datasource.properties")    // di comment karena mau pakai spring boot wrapper
@ImportResource("classpath:sfgdi-config.xml")
@Configuration
public class GreetingServiceConfig {
    //cara ini biasa dipakai kalau class yang akan dipakai itu bukan punya kita
    //sehingga kita ga bisa asal ubah. tapi ini cara jadul banget
    //jadi, ceritanya itu karena class tersebut tidak terdaftar sebagai Stereotype Component,
    //maka kita perlu inisiate class tersebut ke bean biar terdaftar di bean
    //bean yang terdaftar pun menggunakan lower case dari nama class yang terdaftar seperti constructorGreetingService
    //sebenernya bisa2 aja nama bean ga sama dengan nama class, tapi sebaiknya tetap disamakan saja
//    @Bean     //sekarang bisa comment bean karena sudah di set di xml configuration
//    ConstructorGreetingService constructorGreetingService(){
//        return new ConstructorGreetingService();
//    }
    @Bean
    FakeDataSource fakeDataSource(@Value("${guru.username}") String username,
                                  @Value("${guru.password}") String password,
                                  @Value("${guru.jdbcurl}") String jdbcurl){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(username);
        fakeDataSource.setPassword(password);
        fakeDataSource.setJdbcurl(jdbcurl);

        return fakeDataSource;
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

    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return new EnglishGreetingRepositoryImpl();
    }

    //ini di set bean nya sebagai i18nService karena sebelumnya ini di set sebagai @Service("i18nService")
    @Profile("EN")
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository){
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    //dibawah ini, nama bean awalnya i18NSpanishService di override menjadi i18nService.
    //kenapa ga langsung I18NSpanishService i18nService(){ ? karena i18nService sudah dipakai sebelumnya sama English
    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18NSpanishService i18NSpanishService(){
        return new I18NSpanishService();
    }
    @Bean
    PetServiceFactory petServiceFactory(){
        return new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService(PetServiceFactory petServiceFactory){
        return petServiceFactory.getPetService("dog");

    }

    @Profile("cat")
    @Bean
    PetService catPetService(PetServiceFactory petServiceFactory){
        return petServiceFactory.getPetService("cat");
    }
}
