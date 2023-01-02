package fr.systemathicdev.core.products.adapter.output

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import fr.systemathicdev.core.products.domain.Product
import fr.systemathicdev.core.products.domain.ProductDetail

@Keep
data class RestProductList(
    @SerializedName("records")
    @Expose
    val records: List<RestProductDetail>,
){
    fun toProducts() =
        records.map {
            Product(
                id = it.record.id,
                name = it.record.fields.name,
                model = it.record.fields.model,
                date = it.record.fields.date,
                image = it.record.fields.image
            )
        }
}

@Keep
data class RestProductDetail(

    @SerializedName("links")
    @Expose
    val links: List<RestLink>,

    @SerializedName("record")
    @Expose
    val record: RestRecord,
){
    fun toProductDetail() =
        record.fields.let {
            ProductDetail(
                name = it.name,
                model = it.model,
                date = it.date,
                image = it.image,
                category = it.category,
                juridical = it.juridical,
                identification = it.ref,
                geoZone = it.geoZone,
                reason = it.reason,
                warning = it.warning,
                recommendation = it.recommendation,
                pdfLink = it.pdfLink
            )
        }
}

@Keep
data class RestRecord(
    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("timestamp")
    @Expose
    val timestamp: String,

    @SerializedName("size")
    @Expose
    val size: Int,

    @SerializedName("fields")
    @Expose
    val fields: RestField,
)

@Keep
data class RestField(

    @SerializedName("identification_des_produits")
    @Expose
    val ref: String,

    @SerializedName("nature_juridique_du_rappel")
    @Expose
    val juridical: String,

    @SerializedName("categorie_de_produit")
    @Expose
    val category: String,

    @SerializedName("nom_de_la_marque_du_produit")
    @Expose
    val name: String,
    @SerializedName("noms_des_modeles_ou_references")
    @Expose
    val model: String,
    @SerializedName("motif_du_rappel")
    @Expose
    val reason: String,

    @SerializedName("zone_geographique_de_vente")
    @Expose
    val geoZone: String,

    @SerializedName("risques_encourus_par_le_consommateur")
    @Expose
    val warning: String,

    @SerializedName("conduites_a_tenir_par_le_consommateur")
    @Expose
    val recommendation: String,

    @SerializedName("liens_vers_les_images")
    @Expose
    val image: String,

    @SerializedName("lien_vers_affichette_pdf")
    @Expose
    val pdfLink: String,

    @SerializedName("date_ref")
    @Expose
    val date: String,
)

@Keep
data class RestLink(

    @SerializedName("rel")
    @Expose
    val rel: String,

    @SerializedName("href")
    @Expose
    val href: String,
)