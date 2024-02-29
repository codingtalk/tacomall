FROM alpine

WORKDIR /var/www/html

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories \
    && apk update \
    && apk add nginx \
    && apk add nodejs \
    && apk add npm

CMD echo "nginx started" \
    && npm install --force\
    && npm run build \
    && nginx -g "daemon off;"