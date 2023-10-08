package com.example.microservices;

import com.example.microservices.controller.SmartphoneGUIController;
import com.example.microservices.model.Smartphone;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import io.swagger.v3.oas.annotations.media.Schema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.lang.reflect.Field;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MicroServicesArchitectureTest {
    private JavaClasses importedClasses;

    @BeforeEach
    void init(){
        importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.example.microservices");
    }

    @Test
    void testFollowingLayerArchitecture(){
        layeredArchitecture()
                .consideringAllDependencies()
                .layer("Controller").definedBy("..controller")
                .layer("Service").definedBy("..service")
                .layer("Repository").definedBy("..repository")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller","Service")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
                .check(importedClasses);
    }

    @Test
    void controllersShouldHaveEndingNameController(){
        assertThat(importedClasses).isNotNull();
        classes()
                .that().resideInAPackage("..controller..")
                .should()
                .haveSimpleNameEndingWith("Controller")
                .check(importedClasses);


    }
    @Test
    void servicesShouldHaveEndingNameService(){
        classes()
                .that().resideInAPackage("..service..")
                .should()
                .haveSimpleNameEndingWith("Service")
                .check(importedClasses);
    }

    @Test
    void repositoryShouldHaveEndingNameRepository(){
        classes()
                .that().resideInAPackage("..repository..")
                .should()
                .haveSimpleNameEndingWith("Repository")
                .check(importedClasses);
    }
    @Test
    void checkServiceAnnotations(){
        classes()
                .that().resideInAPackage("..service..")
                .should().beAnnotatedWith(Service.class)
                .check(importedClasses);
    }
    @Test
    void checkRepositoryAnnotations(){
        classes()
                .that().resideInAPackage("..repository..")
                .should().beAnnotatedWith(Repository.class)
                .check(importedClasses);
    }
    @Test
    void checkControllersAnnotations(){
        classes()
                .that().resideInAPackage("..controller..")
                .should().beAnnotatedWith(RestController.class)
                .orShould().beAnnotatedWith(Controller.class)
                .check(importedClasses);
    }

    @Test
    void checkControllersAnnotationsRequestMapping(){
        classes()
                .that().resideInAPackage("..controller..")
                .should().beAnnotatedWith(RequestMapping.class)
                .check(importedClasses);
    }

    @Test
    void checkModelOnSwaggerAnnotation(){
        classes()
                .that().resideInAPackage("..model..")
                .should().beAnnotatedWith(Schema.class)
                .check(importedClasses);
    }
    @Test
    void chekModelFieldIdOnAnnotationId(){
        Class<Smartphone> smartphoneClass = Smartphone.class;

        try {
            Field idField = smartphoneClass.getDeclaredField("id");
            assertThat(idField.isAnnotationPresent(Id.class)).isTrue();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testControllerMethodReturnType(){
        ArchRule rule = methods()
                .that().areDeclaredInClassesThat()
                .areAnnotatedWith(Controller.class)
                .and().areDeclaredIn(SmartphoneGUIController.class)
                .should().haveRawReturnType(String.class);
        rule.check(importedClasses);
    }
    @Test
    void testAutowiredAnnotation(){
        noFields()
                .should()
                .beAnnotatedWith(Autowired.class)
                .check(importedClasses);
    }
    @Test
    void checkModelOnMongoDBAnnotation() {
        classes()
                .that().resideInAPackage("..model..")
                .should().beAnnotatedWith(Document.class)
                .orShould().beAnnotatedWith(Id.class)
                .check(importedClasses);
    }
    @Test
    void controllersShouldResideInValidPackage() {
        classes()
                .that().areAnnotatedWith(Controller.class)
                .should().resideInAPackage("..controller..")
                .check(importedClasses);
    }
    @Test
    void servicesShouldResideInValidPackage() {
        classes()
                .that().areAnnotatedWith(Service.class)
                .should().resideInAPackage("..service..")
                .check(importedClasses);
    }
    @Test
    void repositoryShouldResideInValidPackage() {
        classes()
                .that().areAnnotatedWith(Repository.class)
                .should().resideInAPackage("..repository..")
                .check(importedClasses);
    }

}