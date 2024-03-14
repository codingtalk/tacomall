FROM openjdk:17-jdk-alpine

ENV GRADLE_VERSION 7.5.1

WORKDIR /usr/local

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories \
    && apk update \
    && apk add nodejs \
    && apk add npm \
    && apk add curl unzip \
    && curl https://downloads.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip > gradle.zip \
    && unzip gradle.zip \
    && rm gradle.zip

ENV PATH "$PATH:/usr/local/gradle-${GRADLE_VERSION}/bin/"

WORKDIR /app

ENV SPRING_PROFILE test

CMD echo "==============> Current evironment: ${SPRING_PROFILE}" \
    && rm -f -r ./api/open/build \
    && gradle :api-open:build \
    && cd ./api/open/build/libs \
    && java -jar api-open-1.0-SNAPSHOT.jar --spring.profiles.active=${SPRING_PROFILE}


