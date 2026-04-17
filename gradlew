#!/usr/bin/env sh
# ------------------------------------------------------------------------------
# Gradle start up script for UN*X
#
# Copyright 2009-2024 the Gradle project authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS="-Xmx64m"
APP_HOME=`dirname "$0"`
APP_HOME=`cd "$APP_HOME" && pwd`

# Attempt to set APP_HOME to the Java home if a JRE is installed within the Gradle distribution.
if [ -z "$JAVA_HOME" ] ; then
    if [ -x "$APP_HOME/jre/bin/java" ] ; then
        JAVA_HOME="$APP_HOME/jre"
    fi
fi

# Add default JVM options if DRY_RUN is not set.
if [ -z "$JAVA_OPTS" ] ; then
    JAVA_OPTS="$DEFAULT_JVM_OPTS"
fi

# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
else
    JAVACMD=`which java 2>/dev/null`
fi

if [ -z "$JAVACMD" ] ; then
    echo "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH." 1>&2
    exit 1
fi

exec "$JAVACMD" $JAVA_OPTS -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
