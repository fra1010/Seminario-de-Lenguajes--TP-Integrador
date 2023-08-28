package com.example.trabajopracticointegrador

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajopracticointegrador.Pelicula
import com.example.trabajopracticointegrador.PeliculaAdapter
import com.example.trabajopracticointegrador.R




import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var peliculaList: ArrayList<Pelicula>
    private lateinit var peliculaAdapter: PeliculaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saludarUsuario()

        recyclerView= findViewById(R.id.rvMain)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this)

        peliculaList= ArrayList()

        peliculaList.add(Pelicula("El Padrino", "1972","Francis Ford Coppola", "Crimen", R.drawable.padrino,"América, años 40. Don Vito Corleone (Marlon Brando) es el respetado y temido jefe de una de las cinco familias de la mafia de Nueva York. Tiene cuatro hijos: Connie (Talia Shire), el impulsivo Sonny (James Caan), el pusilánime Fredo (John Cazale) y Michael (Al Pacino), que no quiere saber nada de los negocios de su padre. Cuando Corleone, en contra de los consejos de 'Il consigliere' Tom Hagen (Robert Duvall), se niega a participar en el negocio de las drogas, el jefe de otra banda ordena su asesinato. Empieza entonces una violenta y cruenta guerra entre las familias mafiosas."))
        peliculaList.add(Pelicula("Rocky", "1976","John G. Avildsen", "Drama", R.drawable.rocky,"Rocky Balboa es un desconocido boxeador a quien se le ofrece la posibilidad de pelear por el título mundial de los pesos pesados. Con una gran fuerza de voluntad, Rocky se prepara concienzudamente para el combate y también para los cambios que acabarán produciéndose en su vida."))
        peliculaList.add(Pelicula("El club de la pelea", "1999","David Fincher", "Drama psicológico", R.drawable.fight,"Un joven hastiado de su gris y monótona vida lucha contra el insomnio. En un viaje en avión conoce a un carismático vendedor de jabón que sostiene una teoría muy particular: el perfeccionismo es cosa de gentes débiles; sólo la autodestrucción hace que la vida merezca la pena. Ambos deciden entonces fundar un club secreto de lucha, donde poder descargar sus frustaciones y su ira, que tendrá un éxito arrollador."))
        peliculaList.add(Pelicula("El secreto de sus ojos", "2009","Juan José Campanella", "Suspenso", R.drawable.elsecreto,"Benjamín Espósito es oficial de un Juzgado de Instrucción de Buenos Aires recién retirado. Obsesionado por un brutal asesinato ocurrido veinticinco años antes, en 1974, decide escribir una novela sobre el caso, del cual fue testigo y protagonista. Reviviendo el pasado, viene también a su memoria el recuerdo de una mujer, a quien ha amado en silencio durante todos esos años. "))
        peliculaList.add(Pelicula("Batman: El caballero de la noche", "2008","Christopher Nolan", "Acción", R.drawable.batman,"Batman/Bruce Wayne (Christian Bale) regresa para continuar su guerra contra el crimen. Con la ayuda del teniente Jim Gordon (Gary Oldman) y del Fiscal del Distrito Harvey Dent (Aaron Eckhart), Batman se propone destruir el crimen organizado en la ciudad de Gotham. El triunvirato demuestra su eficacia, pero, de repente, aparece Joker (Heath Ledger), un nuevo criminal que desencadena el caos y tiene aterrados a los ciudadanos."))
        peliculaList.add(Pelicula("El Señor de los Anillos: la Comunidad del Anillo", "2001","Peter Jackson", "Fantasía épica", R.drawable.esdla,"En la Tierra Media, el Señor Oscuro Sauron ordenó a los Elfos que forjaran los Grandes Anillos de Poder. Tres para los reyes Elfos, siete para los Señores Enanos, y nueve para los Hombres Mortales. Pero Saurón también forjó, en secreto, el Anillo Único, que tiene el poder de esclavizar toda la Tierra Media. Con la ayuda de sus amigos y de valientes aliados, el joven hobbit Frodo emprende un peligroso viaje con la misión de destruir el Anillo Único. Pero el malvado Sauron ordena la persecución del grupo, compuesto por Frodo y sus leales amigos hobbits, un mago, un hombre, un elfo y un enano. La misión es casi suicida pero necesaria, pues si Sauron con su ejército de orcos lograra recuperar el Anillo, sería el final de la Tierra Media. "))
        peliculaList.add(Pelicula("Primicia Mortal", "2014","Dan Gilroy", "Crimen", R.drawable.primicia,"Tras ser testigo de un accidente, Lou Bloom (Jake Gyllenhaal), un apasionado joven que no consigue encontrar empleo, descubre como forma de ganar dinero el mundo del periodismo criminalista en la peligrosa ciudad de Los Ángeles. Su trabajo es llegar al escenario de crímenes o accidentes y fotografiar lo sucedido para venderlos al mejor postor. "))
        peliculaList.add(Pelicula("Django sin cadenas", "2012","Quentin Tarantino", "Wéstern", R.drawable.django,"En Texas, dos años antes de estallar la Guerra Civil Americana, King Schultz (Christoph Waltz), un cazarrecompensas alemán que sigue la pista a unos asesinos para cobrar por sus cabezas, le promete al esclavo negro Django (Jamie Foxx) dejarlo en libertad si le ayuda a atraparlos. Él acepta, pues luego quiere ir a buscar a su esposa Broomhilda (Kerry Washington), esclava en una plantación del terrateniente Calvin Candie (Leonardo DiCaprio)."))
        peliculaList.add(Pelicula("La isla siniestra", "2010","Martin Scorsese", "Suspenso", R.drawable.islasiniestra,"En el verano de 1954, los agentes judiciales Teddy Daniels (DiCaprio) y Chuck Aule (Ruffalo) son destinados a una remota isla del puerto de Boston para investigar la desaparición de una peligrosa asesina (Mortimer) que estaba recluida en el hospital psiquiátrico Ashecliffe, un centro penitenciario para criminales perturbados dirigido por el siniestro doctor John Cawley (Kingsley). Pronto descubrirán que el centro guarda muchos secretos y que la isla esconde algo más peligroso que los pacientes. Thriller psicológico basado en la novela homónima de Dennis Lehane (autor de Mystic River y Gone Baby Gone)."))

        peliculaAdapter = PeliculaAdapter(peliculaList)
        recyclerView.adapter =peliculaAdapter

        peliculaAdapter.onItemClick = {
            val intent = Intent(this, DescripcionActivity::class.java)
            intent.putExtra("pelicula", it)
            startActivity(intent)
        }

    }

    private fun saludarUsuario() {
        var bundle:Bundle? = intent.extras
        if (bundle !=null){
            var usuario= bundle?.getString("usuario")
            Toast.makeText(this, "Bienvenido/a $usuario", Toast.LENGTH_SHORT).show()
        }
    }
}