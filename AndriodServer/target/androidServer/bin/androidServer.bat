@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\lib

set CLASSPATH="%BASEDIR%"\conf;"%REPO%"\spring-boot-starter-web-1.5.8.RELEASE.jar;"%REPO%"\spring-boot-starter-1.5.8.RELEASE.jar;"%REPO%"\spring-boot-1.5.8.RELEASE.jar;"%REPO%"\spring-boot-starter-logging-1.5.8.RELEASE.jar;"%REPO%"\logback-classic-1.1.11.jar;"%REPO%"\logback-core-1.1.11.jar;"%REPO%"\jcl-over-slf4j-1.7.25.jar;"%REPO%"\jul-to-slf4j-1.7.25.jar;"%REPO%"\log4j-over-slf4j-1.7.25.jar;"%REPO%"\spring-core-4.3.12.RELEASE.jar;"%REPO%"\snakeyaml-1.17.jar;"%REPO%"\spring-boot-starter-tomcat-1.5.8.RELEASE.jar;"%REPO%"\tomcat-embed-core-8.5.23.jar;"%REPO%"\tomcat-annotations-api-8.5.23.jar;"%REPO%"\tomcat-embed-el-8.5.23.jar;"%REPO%"\tomcat-embed-websocket-8.5.23.jar;"%REPO%"\hibernate-validator-5.3.5.Final.jar;"%REPO%"\validation-api-1.1.0.Final.jar;"%REPO%"\jboss-logging-3.3.1.Final.jar;"%REPO%"\jackson-databind-2.8.10.jar;"%REPO%"\jackson-annotations-2.8.0.jar;"%REPO%"\jackson-core-2.8.10.jar;"%REPO%"\spring-web-4.3.12.RELEASE.jar;"%REPO%"\spring-aop-4.3.12.RELEASE.jar;"%REPO%"\spring-beans-4.3.12.RELEASE.jar;"%REPO%"\spring-webmvc-4.3.12.RELEASE.jar;"%REPO%"\spring-expression-4.3.12.RELEASE.jar;"%REPO%"\druid-spring-boot-starter-1.1.0.jar;"%REPO%"\druid-1.1.0.jar;"%REPO%"\spring-boot-autoconfigure-1.5.8.RELEASE.jar;"%REPO%"\mybatis-spring-boot-starter-2.1.3.jar;"%REPO%"\spring-boot-starter-jdbc-1.5.8.RELEASE.jar;"%REPO%"\tomcat-jdbc-8.5.23.jar;"%REPO%"\tomcat-juli-8.5.23.jar;"%REPO%"\spring-jdbc-4.3.12.RELEASE.jar;"%REPO%"\spring-tx-4.3.12.RELEASE.jar;"%REPO%"\mybatis-spring-boot-autoconfigure-2.1.3.jar;"%REPO%"\mybatis-3.5.5.jar;"%REPO%"\mybatis-spring-2.0.5.jar;"%REPO%"\pagehelper-spring-boot-starter-1.2.13.jar;"%REPO%"\pagehelper-spring-boot-autoconfigure-1.2.13.jar;"%REPO%"\pagehelper-5.1.11.jar;"%REPO%"\jsqlparser-2.0.jar;"%REPO%"\mysql-connector-java-5.1.44.jar;"%REPO%"\springfox-swagger2-2.7.0.jar;"%REPO%"\swagger-annotations-1.5.13.jar;"%REPO%"\swagger-models-1.5.13.jar;"%REPO%"\springfox-spi-2.7.0.jar;"%REPO%"\springfox-core-2.7.0.jar;"%REPO%"\byte-buddy-1.6.14.jar;"%REPO%"\springfox-schema-2.7.0.jar;"%REPO%"\springfox-swagger-common-2.7.0.jar;"%REPO%"\springfox-spring-web-2.7.0.jar;"%REPO%"\reflections-0.9.11.jar;"%REPO%"\javassist-3.21.0-GA.jar;"%REPO%"\guava-18.0.jar;"%REPO%"\classmate-1.3.4.jar;"%REPO%"\slf4j-api-1.7.25.jar;"%REPO%"\spring-plugin-core-1.2.0.RELEASE.jar;"%REPO%"\spring-plugin-metadata-1.2.0.RELEASE.jar;"%REPO%"\mapstruct-1.1.0.Final.jar;"%REPO%"\springfox-swagger-ui-2.7.0.jar;"%REPO%"\spring-boot-starter-thymeleaf-1.5.8.RELEASE.jar;"%REPO%"\thymeleaf-spring4-2.1.5.RELEASE.jar;"%REPO%"\thymeleaf-2.1.5.RELEASE.jar;"%REPO%"\ognl-3.0.8.jar;"%REPO%"\unbescape-1.1.0.RELEASE.jar;"%REPO%"\thymeleaf-layout-dialect-1.4.0.jar;"%REPO%"\groovy-2.4.12.jar;"%REPO%"\fastjson-1.2.44.jar;"%REPO%"\slf4j-log4j12-1.7.25.jar;"%REPO%"\log4j-1.2.17.jar;"%REPO%"\lombok-1.16.18.jar;"%REPO%"\shiro-spring-1.3.2.jar;"%REPO%"\shiro-core-1.3.2.jar;"%REPO%"\shiro-web-1.3.2.jar;"%REPO%"\shiro-ehcache-1.3.2.jar;"%REPO%"\ehcache-core-2.5.3.jar;"%REPO%"\spring-boot-starter-cache-1.5.8.RELEASE.jar;"%REPO%"\spring-context-4.3.12.RELEASE.jar;"%REPO%"\spring-context-support-4.3.12.RELEASE.jar;"%REPO%"\ehcache-2.10.4.jar;"%REPO%"\commons-lang3-3.3.2.jar;"%REPO%"\commons-io-2.4.jar;"%REPO%"\commons-beanutils-1.9.1.jar;"%REPO%"\commons-collections-3.2.2.jar;"%REPO%"\jjwt-0.7.0.jar;"%REPO%"\andriod-1.0-SNAPSHOT.jar

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS% -server -Xmx1G -Xms1G -classpath %CLASSPATH% -Dapp.name="androidServer" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" cn.gq.android.StartAndroidServer %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
