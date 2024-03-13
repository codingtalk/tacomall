FROM alpine

WORKDIR /var/www/html

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories \
    && apk update \
    && apk add nginx

CMD echo "nginx started" \
    && nginx -g "daemon off;"