/*
 * Copyright 2009-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Gant script that prepares an JSmooth-based installer
 *
 * @author Andres Almiray
 *
 * @since 0.1
 */

installerPluginBase = getPluginDirForName('installer').file as String
includePluginScript("installer","_Prepare")

target(name: 'preparePackageJsmooth', description: '', prehook: null, posthook: null) {
    event("PreparePackageStart", ['jsmooth'])

    installerWorkDir = "${projectWorkDir}/installer/jsmooth"
    binaryDir = installerWorkDir

    ant.copy(file:"${installerPluginBase}/src/templates/jsmooth/app.jsmooth",
             tofile: "${installerWorkDir}/${griffonAppName}.jsmooth")
    ant.copy(file:"${installerPluginBase}/src/templates/jsmooth/griffon-icon-32x32.png",
             tofile: "${installerWorkDir}/${griffonAppName}-icon.png")
    ant.copy(file:"${installerPluginBase}/src/templates/jsmooth/msvcr71.dll",
             tofile: "${installerWorkDir}/msvcr71.dll")

    File applicationTemplates = new File("${basedir}/src/installer/jsmooth")
    if (applicationTemplates.exists()) {
        ant.copy(todir: installerWorkDir, overwrite: true) {
            fileset(dir: applicationTemplates)
        }
    }

    event("PreparePackageEnd", ['jsmooth'])
}
setDefaultTarget(preparePackageJsmooth)
