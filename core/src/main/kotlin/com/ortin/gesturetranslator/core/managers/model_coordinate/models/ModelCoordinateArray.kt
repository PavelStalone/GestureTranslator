package com.ortin.gesturetranslator.core.managers.model_coordinate.models

 data class ModelCoordinateArray (val coordinate: DoubleArray) {
     override fun equals(other: Any?): Boolean {
         if (this === other) return true
         if (javaClass != other?.javaClass) return false

         other as ModelCoordinateArray
         return coordinate.contentEquals(other.coordinate)
     }

     override fun hashCode(): Int = coordinate.contentHashCode()
 }
