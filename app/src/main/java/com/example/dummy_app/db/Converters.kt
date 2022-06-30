package com.example.dummy_app.db

import android.provider.Telephony
import androidx.room.TypeConverter
import com.example.dummy_app.model.Address

class Converters {
        @TypeConverter
        fun fromSource(address: Address): String? {
            return address.address
        }
        @TypeConverter
        fun toSource(address:String):Address{
            return Address(address,address,address,address)
        }
    }
