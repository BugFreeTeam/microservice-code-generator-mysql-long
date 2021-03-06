package com.anjuxing.platform.code.generator;

import com.anjuxing.platform.code.db.DbCache;
import com.anjuxing.platform.code.entity.CodeModule;
import com.anjuxing.platform.code.entity.DbTable;
import com.anjuxing.platform.code.util.CodeUtils;
import com.anjuxing.platform.code.util.FileUtils;
import com.anjuxing.platform.code.util.FreemarkerUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MyBatis Generator
 */
public class MyBatisGenerator {

    private static MyBatisGenerator instance;
    public static MyBatisGenerator getInstance(){
        if (instance == null) {
            instance = new MyBatisGenerator();
        }
        return instance;
    }

    public void initProject(CodeModule cm) {
        createProjectDir(cm); // Init Project Directory
        if (cm.isCreateMain()){
            createMain(cm);
        }
        if (cm.isCreateTest()){
            createTest(cm);
        }
        if (cm.isCreateDocker()){
            createDocker(cm);
        }
        if (cm.isCreateWiki()){
            createWiki(cm);
        }
    }

    public void createCode(CodeModule cm){
        String projectDir = cm.getSaveDir() + cm.getArtifactId();
        String wikiDir = cm.getSaveDir() + cm.getArtifactId() + ".wiki";
        String mainDir = projectDir + "/src/main";
        String javaDir = mainDir + "/java/"+ CodeUtils.packageToDir(cm.getPackagePath());
        String controllerDir = javaDir + "/controller";
        String modelDir = javaDir + "/model";
        String mapperDir = javaDir + "/mapper";
        String serviceDir = javaDir + "/service";
        String serviceImplDir = javaDir + "/service/impl";
        if (cm.isCreateMain()){
            String javaFtl = "/mybatis/microservice/src/main/java/";
            // Create microservice entity
            FreemarkerUtils.getInstance().build(cm, javaFtl + "/model/model.ftl", modelDir, cm.getEntityName() + ".java");
            // Create microservice mapper
            FreemarkerUtils.getInstance().build(cm, javaFtl + "/mapper/mapper.ftl", mapperDir, cm.getMapperName()+".java");
            // Create microservice mapper xml
            FreemarkerUtils.getInstance().build(cm, javaFtl + "/mapper/mapper-xml.ftl", mapperDir, cm.getMapperName()+".xml");
            // Create microservice service
            FreemarkerUtils.getInstance().build(cm, javaFtl + "/service/service.ftl", serviceDir, cm.getServiceName()+".java");
            // Create microservice serviceImpl
            FreemarkerUtils.getInstance().build(cm, javaFtl + "/service/impl/serviceImpl.ftl", serviceImplDir, cm.getServiceImplName()+".java");
            // Create microservice controller
            FreemarkerUtils.getInstance().build(cm, javaFtl + "/controller/controller.ftl", controllerDir, cm.getControllerName()+".java");
        }

        // Create Wiki
        if (cm.isCreateWiki()){
            wikiDir += "/" + cm.getTableName();
            String wikiName = cm.getTableName();
            // Create wiki entity.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity.ftl", wikiDir, wikiName + ".md");
            // Create wiki entity_get.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_get.ftl", wikiDir, wikiName + "_get.md");
            // Create wiki entity_ids.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_ids.ftl", wikiDir, wikiName + "_ids.md");
            // Create wiki entity_exists.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_exists.ftl", wikiDir, wikiName + "_exists.md");
            // Create wiki entity_list.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_list.ftl", wikiDir, wikiName + "_list.md");
            // Create wiki entity_page.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_page.ftl", wikiDir, wikiName + "_page.md");
            // Create wiki entity_add.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_add.ftl", wikiDir, wikiName + "_add.md");
            // Create wiki entity_update.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_update.ftl", wikiDir, wikiName + "_update.md");
            // Create wiki entity_cancel.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_cancel.ftl", wikiDir, wikiName + "_cancel.md");
            // Create wiki entity_delete.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_delete.ftl", wikiDir, wikiName + "_delete.md");
            // Create wiki entity_batch_add.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_batch_add.ftl", wikiDir, wikiName + "_batch_add.md");
            // Create wiki entity_batch_update.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_batch_update.ftl", wikiDir, wikiName + "_batch_update.md");
            // Create wiki entity_batch_cancel.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_batch_cancel.ftl", wikiDir, wikiName + "_batch_cancel.md");
            // Create wiki entity_batch_delete.md
            FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/entity/entity_batch_delete.ftl", wikiDir, wikiName + "_batch_delete.md");

        }
    }

    private void createMain(CodeModule cm){
        String projectDir = cm.getSaveDir() + cm.getArtifactId();
        String mainDir = projectDir + "/src/main";
        String javaDir = mainDir + "/java/"+ CodeUtils.packageToDir(cm.getPackagePath());
        String commonDir = javaDir + "/common";
        String configDir = javaDir + "/config";
        String resourcesDir = mainDir + "/resources";
        // Create microservice pom.xml
        FreemarkerUtils.getInstance().build(cm, "/mybatis/microservice/pom.ftl", projectDir, "pom.xml");
        // Create microservice readme.md
        FreemarkerUtils.getInstance().build(cm, "/mybatis/microservice/readme.ftl", projectDir, "README.md");
        // Create microservice .gitignore
        FreemarkerUtils.getInstance().build(cm, "/mybatis/microservice/gitignore.ftl", projectDir, ".gitignore");
        // Create microservice application.java
        FreemarkerUtils.getInstance().build(cm, "/mybatis/microservice/src/main/java/application.ftl", javaDir, cm.getProjectName()+"Application.java");
        // Create microservice GlobalExceptionHandler
        FreemarkerUtils.getInstance().build(cm, "/mybatis/microservice/src/main/java/common/GlobalExceptionHandler.ftl", commonDir, "GlobalExceptionHandler.java");
        // Create microservice CorsConfig
        FreemarkerUtils.getInstance().build(cm, "/mybatis/microservice/src/main/java/config/CorsConfig.ftl", configDir, "CorsConfig.java");
        // Create microservice DruidConfig
        FreemarkerUtils.getInstance().build(cm, "/mybatis/microservice/src/main/java/config/DruidConfig.ftl", configDir, "DruidConfig.java");
        // Create microservice SessionFactoryConfig
        FreemarkerUtils.getInstance().build(cm, "/mybatis/microservice/src/main/java/config/SessionFactoryConfig.ftl", configDir, "SessionFactoryConfig.java");
        // Create microservice resources application.properties
        FreemarkerUtils.getInstance().build(cm, "/mybatis/microservice/src/main/resources/application.properties.ftl", resourcesDir, "application.properties");
        // Create microservice resources application.yml
        FreemarkerUtils.getInstance().build(cm, "/mybatis/microservice/src/main/resources/application.yml.ftl", resourcesDir, "application.yml");// Create microservice resources application.yml
        // Create microservice resources mybatis.xml
        FreemarkerUtils.getInstance().build(cm, "/mybatis/microservice/src/main/resources/mybatis.xml.ftl", resourcesDir, "mybatis.xml");
    }

    private void createTest(CodeModule cm){

    }

    private void createDocker(CodeModule cm){
        String projectDir = cm.getSaveDir() + cm.getArtifactId();
        String mainDir = projectDir + "/src/main";
        String dockerDir = mainDir + "/docker";
        FreemarkerUtils.getInstance().build(cm, "/mybatis/microservice/src/main/docker/Dockerfile.ftl", dockerDir, "Dockerfile");
    }

    private void createWiki(CodeModule cm){
        String wikiDir = cm.getSaveDir() + cm.getArtifactId() + ".wiki";
        // Create Wiki Home
        if (cm.isCreateWiki()){
            Map<String, List<DbTable>> dbMap = new HashMap<String, List<DbTable>>();
            dbMap.put("tableList", DbCache.mysqlDbTableList);
            FreemarkerUtils.getInstance().build(dbMap, "/mybatis/wiki/home.ftl", wikiDir, "home.md");
        }
        // Create wiki .gitignore
        FreemarkerUtils.getInstance().build(cm, "/mybatis/wiki/gitignore.ftl", wikiDir, ".gitignore");
    }

    private void createProjectDir(CodeModule cm){
        // Create microservice project directory
        String projectDir = cm.getSaveDir() + cm.getArtifactId();
        String wikiDir = cm.getSaveDir() + cm.getArtifactId() + ".wiki";
        String mainDir = projectDir + "/src/main";
        String testDir = projectDir + "/src/test";
        String dockerDir = mainDir + "/docker";
        String javaDir = mainDir + "/java/"+CodeUtils.packageToDir(cm.getPackagePath());
        String resourcesDir = mainDir + "/resources";
        String commonDir = javaDir + "/common";
        String configDir = javaDir + "/config";
        String controllerDir = javaDir + "/controller";
        String modelDir = javaDir + "/model";
        String mapperDir = javaDir + "/mapper";
        String serviceDir = javaDir + "/service";
        String serviceImplDir = javaDir + "/service/impl";

        if (cm.isCreateMain()){
            FileUtils.createDir(projectDir);
            FileUtils.createDir(mainDir);
            FileUtils.createDir(javaDir);
            FileUtils.createDir(resourcesDir);
            FileUtils.createDir(commonDir);
            FileUtils.createDir(configDir);
            FileUtils.createDir(controllerDir);
            FileUtils.createDir(modelDir);
            FileUtils.createDir(mapperDir);
            FileUtils.createDir(serviceDir);
            FileUtils.createDir(serviceImplDir);
        }
        if (cm.isCreateDocker()){
            FileUtils.createDir(dockerDir);
        }
        if (cm.isCreateTest()){
            FileUtils.createDir(testDir);
        }
        if (cm.isCreateWiki()){
            FileUtils.createDir(wikiDir);
        }
    }

}
