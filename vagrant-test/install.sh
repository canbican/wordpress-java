INSTALLED=$(dpkg -l|grep apache2)
mkdir -p ~/.wp-cli
rm -f ~/.wp-cli/config.yml
ln -s /var/www/wp-cli.yml ~/.wp-cli/config.yml
if [ -z "$INSTALLED" ]
then
  sudo apt-get update
  sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password password root'
  sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password root'
  sudo apt-get install -y vim curl python-software-properties
  sudo add-apt-repository -y ppa:ondrej/php5
  sudo apt-get update
  sudo apt-get install -y php5 apache2 libapache2-mod-php5 php5-curl php5-gd php5-mcrypt php5-readline mysql-server-5.5 php5-mysql git-core php5-xdebug avahi-daemon nullmailer
  cat << EOF | sudo tee -a /etc/php5/mods-available/xdebug.ini
xdebug.scream=1
xdebug.cli_color=1
xdebug.show_local_vars=1
EOF
  sudo a2enmod rewrite
  sed -i "s/error_reporting = .*/error_reporting = E_ALL/" /etc/php5/apache2/php.ini
  sed -i "s/display_errors = .*/display_errors = On/" /etc/php5/apache2/php.ini
  sed -i "s/disable_functions = .*/disable_functions = /" /etc/php5/cli/php.ini
  sudo service apache2 restart
fi

if [ ! -f /usr/local/bin/composer ]
then
  curl -sS https://getcomposer.org/installer | php
  sudo mv composer.phar /usr/local/bin/composer
fi

if [ ! -f /usr/local/bin/wp ]
then
  curl -O https://raw.githubusercontent.com/wp-cli/builds/gh-pages/phar/wp-cli.phar
  chmod +x wp-cli.phar
  mv wp-cli.phar /usr/local/bin/wp
fi
if [ ! -d /var/www/html/wp-admin ]
then
  rm -rf /var/www/html
  mkdir -p /var/www/html
  chown www-data:www-data /var/www/html
  sudo -u www-data -- bash -c "cd /var/www/html; wp core download"
fi
rm -f /var/www/html/wp-config.php
sudo -u www-data -- bash -c "cd /var/www/html; wp core config --dbuser=root --dbpass=root --dbname=blog"
sudo -u www-data -- bash -c "cd /var/www/html; wp db drop --yes"
sudo -u www-data -- bash -c "cd /var/www/html; wp db create"
sudo -u www-data -- bash -c "cd /var/www/html; wp core install --url=wordpressjava-test.local --title=WordpressJavaTest --admin_user=admin --admin_password=admin --admin_email=nowhere@none.co.coco"
sudo -u www-data -- bash -c "cd /var/www/html; wp user create testuser testuser@example.com.cox"
sudo -u www-data -- bash -c "cd /var/www/html; wp plugin install wordpress-importer --activate"
sudo -u www-data -- bash -c "cd /var/www/html; wp media import /var/www/Lenna.jpg"
