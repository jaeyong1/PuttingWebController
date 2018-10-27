netsh interface portproxy delete v4tov4 listenport=3306
netsh interface portproxy add v4tov4 listenport=3306 connectport=3306 connectaddress=puttingonetest.cafe24.com
netsh interface portproxy show all
copy root-context_Active_puttingoneTest.xml ..\src\main\webapp\WEB-INF\spring\root-context.xml