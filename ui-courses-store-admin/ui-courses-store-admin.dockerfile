FROM node:12.2.0-alpine
EXPOSE 8080
WORKDIR /var/www
COPY ./ui-courses-store-admin .

RUN npm install
RUN npm install @vue/cli -g

ENTRYPOINT npm run dev

