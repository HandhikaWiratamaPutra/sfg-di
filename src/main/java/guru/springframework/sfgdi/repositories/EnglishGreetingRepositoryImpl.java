package guru.springframework.sfgdi.repositories;

public class EnglishGreetingRepositoryImpl implements EnglishGreetingRepository {

    @Override
    public String getGreeting() {
        System.out.println("Hello World - EN from repository at sout");
        return "Hello World - EN from repository";
    }
}
