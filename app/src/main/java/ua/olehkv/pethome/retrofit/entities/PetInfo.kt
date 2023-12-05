package ua.olehkv.pethome.retrofit.entities

import com.google.gson.annotations.SerializedName

data class PetInfo(
    @SerializedName("basicInfo"       ) var basicInfo     : BasicInfo      = BasicInfo(),
    @SerializedName("photos"          ) var photos        : ArrayList<String> = arrayListOf(),
    @SerializedName("dogAttributes"   ) var dogAttributes : DogAttributes?    = null,
    @SerializedName("catAttributes"   ) var catAttributes : CatAttributes?    = null,
    @SerializedName("rodentAttributes") var rodentAttributes : RodentAttributes? = null,
    @SerializedName("fishAttributes"  ) var fishAttributes : FishAttributes?   = null
)

data class BasicInfo (
    @SerializedName("name"                   ) var name                 : String? = null,
    @SerializedName("age"                    ) var age                  : Int?    = null,
    @SerializedName("gender"                 ) var gender               : String? = null,
    @SerializedName("color"                  ) var color                : String? = null,
    @SerializedName("available_for_adoption" ) var availableForAdoption : Int?    = null,
    @SerializedName("adoption_fee"           ) var adoptionFee          : String? = null,
    @SerializedName("description"            ) var description          : String? = null,
    @SerializedName("shelterName"            ) var shelterName          : String? = null,
    @SerializedName("contact_email"          ) var contactEmail         : String? = null,
    @SerializedName("contact_phone"          ) var contactPhone         : String? = null,
    @SerializedName("street"                 ) var street               : String? = null,
    @SerializedName("city"                   ) var city                 : String? = null,
    @SerializedName("state"                  ) var state                : String? = null,
    @SerializedName("postal_code"            ) var postalCode           : String? = null,
    @SerializedName("country"                ) var country              : String? = null
)


data class DogAttributes (
    @SerializedName("dog_id"            ) var dogId           : Int?    = null,
    @SerializedName("breed_id"          ) var breedId         : Int?    = null,
    @SerializedName("size"              ) var size            : String? = null,
    @SerializedName("weight"            ) var weight          : String? = null,
    @SerializedName("is_house_trained"  ) var isHouseTrained  : Int?    = null,
    @SerializedName("is_microchipped"   ) var isMicrochipped  : Int?    = null,
    @SerializedName("is_neutered"       ) var isNeutered      : Int?    = null,
    @SerializedName("dog_attributes_id" ) var dogAttributesId : Int?    = null,
    @SerializedName("breed_name"        ) var breedName       : String? = null
)

data class CatAttributes (

    @SerializedName("cat_id"            ) var catId           : Int?    = null,
    @SerializedName("breed_id"          ) var breedId         : Int?    = null,
    @SerializedName("size"              ) var size            : String? = null,
    @SerializedName("weight"            ) var weight          : String? = null,
    @SerializedName("is_house_trained"  ) var isHouseTrained  : Int?    = null,
    @SerializedName("is_neutered"       ) var isNeutered      : Int?    = null,
    @SerializedName("cat_attributes_id" ) var catAttributesId : Int?    = null,
    @SerializedName("breed_name"        ) var breedName       : String? = null,
    @SerializedName("photo_url"         ) var photoUrl        : String? = null

)

data class RodentAttributes (

    @SerializedName("rodent_id"       ) var rodentId       : Int?    = null,
    @SerializedName("type_id"         ) var typeId         : Int?    = null,
    @SerializedName("size"            ) var size           : String? = null,
    @SerializedName("weight"          ) var weight         : String? = null,
    @SerializedName("is_domesticated" ) var isDomesticated : Int? = null,
    @SerializedName("gender"          ) var gender         : String? = null,
    @SerializedName("type_name"       ) var typeName       : String? = null,
    @SerializedName("photo_url"       ) var photoUrl       : String? = null

)
data class FishAttributes (
    @SerializedName("fish_id"      ) var fishId      : Int?    = null,
    @SerializedName("type_id"      ) var typeId      : Int?    = null,
    @SerializedName("size"         ) var size        : String? = null,
    @SerializedName("color"        ) var color       : String? = null,
    @SerializedName("is_saltwater" ) var isSaltwater : String? = null,
    @SerializedName("type_name"    ) var typeName    : String? = null,
    @SerializedName("photo_url"    ) var photoUrl    : String? = null
)
