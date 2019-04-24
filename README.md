# Automacao_appium_colab
Projeto de automação de testes para Android, utilizando o framework Appium.


**Ferramentas utilizadas para automação de testes do AppColaborador.
    • Appium
        ◦ 	Framework open-source  que utiliza o protocolo WebDriver para realizar testes em apps nativos e híbridos. O framework utiliza-se de um webserver REST para fazer a conexão entre o cliente e o dispositivo mobile. Assim, os testes podem ser feitos a partir de qualquer linguagem que tenha suporte a alguma API HTTP. Porém, disponilbilizam-se bibliotecas para algumas linguagens, facilitando o desenvolvimento dos testes.

    • UiAutomator2
        ◦ 	O Appium se utiliza de driver de automação já fornecidos para realizar os testes, tais como UiAutomator/UiAutomator2, Expresso e XCUITest. Nesse projeto, foi utilizador o driver UiAutomator2, que é o principal meio de automação fornecido pela Google para testes em Android. O mesmo não necessita de acesso ao código-fonte, tornado-o viável para testes de caixa preta. Para se realizar os testes de caixa preta com o UiAutomator2, é necessário tambem que seja utilizado um software auxiliar fornecido no SDK Android, que é chamado de UiAutomatorViewer, que permite que seja feita uma análise da hierarquia de layout dos componentes apresentada no dispositivo Android, sendo utilizada para que sejam localizado os ID’s e as propiedades dos componentes a serem testados.
