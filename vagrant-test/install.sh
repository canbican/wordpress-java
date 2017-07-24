#!/bin/bash

WHO=ubuntu
WHOG=ubuntu
VDIR=/var/www/html

export PATH="/usr/local/bin:$PATH"
sudo mkdir -p ${VDIR}

sudo apt-get install software-properties-common
sudo add-apt-repository -y ppa:ondrej/php
sudo apt update
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password password root'
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password root'
sudo apt-get -qq -y --force-yes install apache2 libapache2-mod-fastcgi php5.6-cli php5.6 php5.6-gd php5.6-mcrypt php5.6-readline php5.6-mysql php5.6-xdebug mysql-server php5.6-mysql avahi-daemon php5.6-xml
sudo a2enmod rewrite actions fastcgi alias

mkdir -p ~/.wp-cli
if [ ! -e /usr/local/bin/wp ]
then
  curl -s -O https://raw.githubusercontent.com/wp-cli/builds/gh-pages/phar/wp-cli.phar
  chmod +x wp-cli.phar
  sudo mv wp-cli.phar /usr/local/bin/wp
fi

cp /vagrant/wp-cli.yml ~
cd ~
sudo rm -rf ${VDIR}/*
sudo chown -Rh ${WHO}:${WHOG} ${VDIR}
sudo chmod -R a+rwx $VDIR
echo 'drop database blog' | mysql -uroot -proot
sudo -u $WHO -i -- wp core download
sudo -u $WHO -i -- wp core config
sudo -u $WHO -i -- wp db create
sudo -u $WHO -i -- wp core install
sudo -u $WHO -i -- wp user create testuser testuser@example.com.cox
#wp plugin install wordpress-importer --activate
sudo -u $WHO -i -- wp media import /vagrant/Lenna.jpg
sudo chmod -R a+rwx $VDIR
sudo service apache2 restart
