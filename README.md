Via Linha de Comando (Recomendado)
Execute o comando na raiz do projeto para salvar o arquivo bruto direto na pasta de destino:

- Linux / macOS / Git Bash (usando curl):

mkdir -p src/main/resources/wsdl
curl -o src/main/resources/wsdl/CountryInfoService.wsdl "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL"

- Windows PowerShell:

New-Item -ItemType Directory -Force -Path "src/main/resources/wsdl"
Invoke-WebRequest -Uri "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL" -OutFile "src/main/resources/wsdl/CountryInfoService.wsdl"

- Pelo Navegador

Acesse o link [http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL](http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL).

Pressione Ctrl + S (ou clique com botão direito > Salvar como...).



#NUNCA COPIAR DIRETO E COLOCAR PODE VIR CARACTERES ESTRANHOS 

Acessando o Sistemas

curl -X GET http://localhost:8080/projeto-teste-wsdl/ListarNomeContinentes