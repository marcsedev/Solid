package com.marcsogasdev.solid


    fun main() {

        //S.O.L.I.D

        /**
        S- single responsability principle (responsabilidad única)
         */

        //ERROR
        //1- almacena las propiedades del usuario (id, nombre)
        //2 - Realiza operaciones en base de datos
        data class User(val id: Int, val name: String) {

            fun user(id: Int): User {

                //Búsqueda en base de datos
                return User(0, "")
            }

        }

        //CORRECT
        // Se resuelve creando dos entidades

        data class User2(val id: Int, val name: String)

        class UserDB {

            fun user(id: Int): User2 {
                //Búsqueda en base de datos
                return User2(0, "")

            }
        }


        /**
        O - Open-close prinicple (principio abierto-cerrado)
         */

        //ERROR

        //para añadir un nuevo poligono en este caso deberíamos modificar
        //la función calculateArea ya que no está contemplado en ella

        data class Triangle(val lenght: Double, val height: Double)
        data class Circle(val radius: Double)

        class Area {
            fun calculateArea(polygon: Any): Double? {
                return when (polygon) {
                    is Triangle -> {
                        (polygon.lenght * polygon.height) / 2
                    }
                    is Circle -> {
                        Math.PI * (polygon.radius * polygon.radius)
                    }
                    else -> {
                        null
                    }
                }

            }


        }
    }

        //CORRECT

        // de este modo podemos añadir poligonos sin modificar la funcion area
        // solo añadimos nuevos poligonos

        //Se crea la interface y luego se sobreescribe en cada poligono

        interface  Polygon{

            fun area():Double
        }

        data class Triangle2 (val lenght:Double,val height:Double):Polygon{

            override fun area():Double{
                return (lenght*height)/2
            }
        }

        data class Circle2 (val radius:Double):Polygon{
            override fun area():Double{
                return Math.PI*(radius*radius)
            }
        }

        //Borramos el codigo de la clase Area y llamar a un poligono y su funcion area
        //así cada poligono ejecuta la funcion de area correspondiente
        class Area2 {
            fun calculateArea2(polygon: Polygon):Double?{
                return polygon.area()
            }
        }

