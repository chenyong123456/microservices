package lyw.demo.dao;

import org.springframework.stereotype.Component;
import zipkin2.Span;
import zipkin2.reporter.Reporter;

//@Component("zipkinReporter")
public class LoggerDao implements Reporter<Span> {
    @Override
    public void report(Span span) {
        System.out.println(span.toString());

    }
}
