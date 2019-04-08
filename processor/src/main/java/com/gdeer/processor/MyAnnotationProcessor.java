package com.gdeer.processor;

import com.google.auto.service.AutoService;

import com.gdeer.annotation.BindView;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class MyAnnotationProcessor extends AbstractProcessor {
    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessager = processingEnvironment.getMessager();
        processingEnvironment.getFiler();
        mMessager.printMessage(Diagnostic.Kind.NOTE, "MyAnnotationProcessor init");
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        mMessager.printMessage(Diagnostic.Kind.NOTE, "MyAnnotationProcessor getSupportedSourceVersion");
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        mMessager.printMessage(Diagnostic.Kind.NOTE, "MyAnnotationProcessor getSupportedAnnotationTypes ");
        Set<String> strings = new HashSet<>();
        strings.add(BindView.class.getCanonicalName());
        return strings;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, "MyAnnotationProcessor process " + roundEnvironment.hashCode());
        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        mMessager.printMessage(Diagnostic.Kind.NOTE, "MyAnnotationProcessor 要处理的注解 " + set);
        mMessager.printMessage(Diagnostic.Kind.NOTE, "MyAnnotationProcessor 注解修饰的语句 " + elementSet);
        if (elementSet != null) {
            for (Element element : elementSet) {
                VariableElement variableElement = (VariableElement) element;
                Name name = variableElement.getSimpleName();
                BindView bindView = variableElement.getAnnotation(BindView.class);
                mMessager.printMessage(Diagnostic.Kind.NOTE, "MyAnnotationProcessor 注解修饰的语句信息和注解元素值 " + name
                    + ", " + bindView.value());
            }
        }
        return true;
    }
}
