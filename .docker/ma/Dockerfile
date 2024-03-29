FROM openjdk:17-jdk-alpine

ENV GRADLE_VERSION 7.5.1

WORKDIR /tmp

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories \
    && apk update \
    && apk add curl libstdc++ unzip \
    && curl https://unofficial-builds.nodejs.org/download/release/v18.16.0/node-v18.16.0-linux-x64-musl.tar.gz > ./node-v18.16.0-linux-x64-musl.tar.gz \
    && mkdir -p /tmp/nodejs \
    && tar -zxvf *.tar.gz --directory /tmp/nodejs --strip-components=1 \
    && mv /tmp/nodejs/bin/node /usr/local/bin/node \
    && mv /tmp/nodejs/bin/npm /usr/local/bin/npm \
    && curl -fsSL "https://github.com/pnpm/pnpm/releases/latest/download/pnpm-linuxstatic-x64" -o /usr/local/bin/pnpm && chmod +x /usr/local/bin/pnpm

WORKDIR /usr/local

RUN curl https://downloads.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip > gradle.zip \
    && unzip gradle.zip \
    && rm gradle.zip

ENV PATH "$PATH:/usr/local/gradle-${GRADLE_VERSION}/bin/"

WORKDIR /var/www/html

ENV SPRING_PROFILE test

CMD echo "==============> Current evironment: ${SPRING_PROFILE}" \
    && pnpm config set store-dir "/root/.pnpm-store" \
    && pnpm install \
    && pnpm run build:h5 \
    && cd /app \
    && rm -f -r ./api/ma/build \
    && gradle :api-ma:build \
    && cd ./api/ma/build/libs \
    && java -jar api-ma-1.0-SNAPSHOT.jar --spring.profiles.active=${SPRING_PROFILE}


