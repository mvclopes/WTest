# WTest

O aplicativo `WTest` foi desenvolvido utilizando o padrão de arquitetura de apresentação [MVVM](https://developer.android.com/jetpack/guide?hl=pt-br) e os dados que são consumidos pela aplicação são códigos postais de Portugal, disponibilizados pela CTT.

### Tecnologias utilizadas
Foram utilizadas as seguintes tecnologias:
* Injeção de dependência com [Koin](https://insert-koin.io/)
* Persistência de dados com [Room](https://developer.android.com/training/data-storage/room)
* Download de arquivos com [Android Download Manager](https://developer.android.com/reference/android/app/DownloadManager)
* Testes unitários com [Mockk](https://mockk.io/)
* Programação assíncrona com [Flow](https://developer.android.com/kotlin/flow?hl=pt-br) e [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
* Navegação com [Android Jetpack Navigation](https://developer.android.com/guide/navigation?hl=pt-br)
* Vinculação de visualizações com [View Binding](https://developer.android.com/topic/libraries/view-binding)

### Fluxo de funcionamento

1. Usuário concedeu permissões necessárias?
* Sim: Verificar se arquivo `.csv` já foi baixado.
* Não: Solicitar permissão.
2. Arquivo `.csv` já foi baixado?
* Sim: Verificar se banco de dados está vazio.
* Não: Registrar-se ao BroadcastReceiver para ser notificado ao evento de conclusão de download de tal arquivo.
3. Banco de dados vazio?
* Sim: Ler arquivo `.csv` e persistir dados no banco de dados.
* Não: Obter dados do banco de dados.
4. Exibir lista de códigos de postais ao usuário.
5. Pesquisa de determinado código postal.

### Próximas etapas
* Modularizar as funcionalidades (Ex.: banco de dados, serviço de download de arquivos).
* Criação de testes instrumentados.
* Implementação de novas funcionalidades (Ex.: tela de detalhes e de cadastro de código postal).