package br.com.newsapp.data.db

interface Databases {

    object Login {
        const val TABLE_NAME: String = "login_table"

        const val idDoc: String = "idDocumento"
        const val hat: String = "chapeu"
    }

    object News {
        const val TABLE_NAME: String = "news_table"

        const val idDoc: String = "idDocumento"
        const val hat: String = "chapeu"
        const val title: String = "titulo"
        const val thinLine: String = "linhaFina"
        const val dateTimePublication: String = "dataHoraPublicacao"
        const val url: String = "url"
        const val image: String = "imagem"
        const val source: String = "source"
        const val credit: String = "credito"
        const val imageCredit: String = "imagemCredito"
    }

    object Document {
        const val TABLE_NAME: String = "doc_table"

        const val url: String = "url"
        const val source: String = "source"
        const val product: String = "produto"
        const val editorial: String = "editoria"
        const val subEditorial: String = "subeditoria"
        const val title: String = "titulo"
        const val credit: String = "credito"
        const val datePub: String = "datapub"
        const val timePub: String = "horapub"
        const val thinLine: String = "linhaFina"
        const val image: String = "imagem"
        const val thumbnail: String = "thumbnail"
        const val creditImage: String = "creditoImagem"
        const val legendImage: String = "legendaImagem"
        const val origin: String = "origem"
        const val id: String = "id"
        const val formattedBody: String = "corpoformatado"
    }


}