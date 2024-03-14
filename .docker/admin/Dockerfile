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

WORKDIR /var/www/html

ENV SPRING_PROFILE test

CMD echo "==============> Current evironment: ${SPRING_PROFILE}" \
    && npm install \
    && npm run build \
    && cd /app \
    && rm -f -r ./api/admin/build \
    && gradle :api-admin:build \
    && cd ./api/admin/build/libs \
    && java -jar api-admin-1.0-SNAPSHOT.jar --spring.profiles.active=${SPRING_PROFILE}


