package br.com.meiadois.decole.data.localdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Company(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val thumbnail: String,
    val cep: String,
    val banner: String,
    val cnpj: String,
    val cellphone: String,
    val email: String,
    val description: String,
    val visible: Boolean,
    val city: String?,
    val neighborhood: String?,
    val segmentId: Int
)