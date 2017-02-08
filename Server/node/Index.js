const TelegramBot = require('node-telegram-bot-api');
const fs = require('fs');

const TOKEN = {You TelegramBot Token};
const BOTNAME = {You TelegramBot Name};
const TIMEOUT = 60;
const bot = new TelegramBot(TOKEN, {polling: true});

const PORT = {PORT};
const HOST = {IP};
const dgram = require('dgram');
const server = dgram.createSocket('udp4');

console.log(BOTNAME+' login!');


server.on('listening', function(){
  let address = server.address();
  console.log(address+"실행"+address.address+":"+address.port);
});

server.on('message', function(message, remote){
  console.log(remote.address+':'+remote.port+":"+message);

  bot.sendMessage({Telegram Group ID},message,{parse_mode: 'markdown'});
});


server.bind(PORT,HOST);
