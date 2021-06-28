package lk.ac.sjp.foe.co4353.g6.answerservice;

import lk.ac.sjp.foe.co4353.g6.answerservice.model.Answer;
import lk.ac.sjp.foe.co4353.g6.answerservice.repository.AnswerRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
public class AnswerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnswerServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner init(AnswerRepository repository) {
        return args -> Stream.of(
                new Answer(
                        1L,
                        "A reference can be thought of as a constant pointer (not to be confused with a pointer to a constant value!) with automatic indirection, ie the compiler will apply the * operator for you.\n" +
                                "All references must be initialized with a non-null value or compilation will fail. It's neither possible to get the address of a reference - the address operator will return the address of the referenced value instead - nor is it possible to do arithmetics on references.\n" +
                                "C programmers might dislike C++ references as it will no longer be obvious when indirection happens or if an argument gets passed by value or by pointer without looking at function signatures.\n" +
                                "C++ programmers might dislike using pointers as they are considered unsafe - although references aren't really any safer than constant pointers except in the most trivial cases - lack the convenience of automatic indirection and carry a different semantic connotation.",
                        2L,
                        2L,
                        new Date(),
                        new Date()
                ),
                new Answer(
                        1L,
                        "Apart from syntactic sugar, a reference is a const pointer (not pointer to a const). You must establish what it refers to when you declare the reference variable, and you cannot change it later.\n" +
                                "Update: now that I think about it some more, there is an important difference.\n" +
                                '\n' +
                                "A const pointer's target can be replaced by taking its address and using a const cast.\n" +
                                '\n' +
                                "A reference's target cannot be replaced in any way short of UB.\n" +
                                '\n' +
                                "This should permit the compiler to do more optimization on a reference.",
                        3L,
                        3L,
                        new Date(),
                        new Date()
                ),
                new Answer(
                        2L,
                        "(Caveat: I am not a Java programmer, I am a Perl programmer. Perl has no formal protections which is perhaps why I understand the problem so well :) )\n" +
                                '\n' +
                                "Private:\n" +
                                "Like you'd think, only the class in which it is declared can see it.\n" +
                                '\n' +
                                "Package Private:\n" +
                                "It can only be seen and used by the package in which it was declared. This is the default in Java (which some see as a mistake).\n" +
                                '\n' +
                                "Protected:\n" +
                                "Package Private + can be seen by subclasses or package members.\n" +
                                '\n' +
                                "Public:\n" +
                                "Everyone can see it.",
                         1L,
                        1L,
                        new Date(),
                        new Date()
                ),
                new Answer(
                        2L,
                        "Easy rule. Start with declaring everything private. And then progress towards the public as the needs arise and design warrants it.\n" +
                                '\n' +
                                "When exposing members ask yourself if you are exposing representation choices or abstraction choices. The first is something you want to avoid as it will introduce too many dependencies on the actual representation rather than on its observable behavior.\n" +
                                '\n' +
                                "As a general rule I try to avoid overriding method implementations by subclassing; it's too easy to screw up the logic. Declare abstract protected methods if you intend for it to be overridden.\n" +
                                '\n' +
                                "Also, use the @Override annotation when overriding to keep things from breaking when you refactor.",
                        3L,
                        3L,
                        new Date(),
                        new Date()
                ),
                new Answer(
                        3L,
                        "For me it might be C.\n" +
                                "\n" +
                                "C is often said to be a “portable assembler”, so if we consider that, then it’s really quite incredible what C provided, which is practically all the power of assembler in an easy to use language which work as well on Intel and ARM as it does on MIPS and SPARC. C makes completely different computer architectures look exactly the same to the programmer, which is fairly amazing if we think about it.\n" +
                                "\n" +
                                "I’m too lazy to use C for everything, and in terms of sheer productivity, I’ll take Java, but I think C was (and is) the greater innovation, it completely defines the computing landscape we’re in today.\n",
                        2L,
                        2L,
                        new Date(),
                        new Date()
                ),
                new Answer(
                        3L,
                        "This is one of three questions you can ask a group of software engineers if you actually want to start a fist fight!\n" +
                                "\n" +
                                "(The other two are: “Which Operating system is the best?” and “Which text editor is the best?”)\n" +
                                "\n" +
                                "So - the DIPLOMATIC answer that is least likely to result in open warfare is this:\n" +
                                "\n" +
                                "Every programming language was created to fulfill a need. If some other language existed that could also fulfill that need, then nobody in their right mind would go to all the trouble of making an entire new language.\n" +
                                "\n" +
                                "What this means is, that (in general) every language in existence that is not utterly obsoleted is “The Best” for some particular application.",
                        1L,
                        1L,
                        new Date(),
                        new Date()
                )
        ).forEach(repository::save);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

