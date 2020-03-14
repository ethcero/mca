FROM node:10.19.0-slim

WORKDIR /usr/local/src/mca

COPY package.json ./
RUN npm i

COPY src ./src
COPY public ./public

EXPOSE 8080

ENTRYPOINT [ "node", "/usr/local/src/mca/src/app.js" ]