package it.polimi.ingsw;

//prova
private class Partita {

    int numGiocatori;
    Giocatore turno; //riferimento al giocatore di turno
    ObiettivoComune obiettivo[]; //array di dimensione 2 per entrambi gli obiettivi pescati

    void nuovaPartita() {};

    class Giocatore {
        String nome;
        int punti;
        int primoGiocatore; //1 si, 0 no

        ObiettivoPrivato obiettivo;

        //matrice 6x5
        class Libreria {
            Tessera slots[][]; //riferimento a tessera se slot pieno, void altrimenti, si riempie dal basso
        }

    }

    //soggiorno, matrice 9x9, slot ad angolo inutilizzati
    class Scacchiera {
        Tessera slots[][]; //riferimento a tessera se slot pieno, void altrimenti
    }

    class Tessera {
        enum tipo { //tessere dello stesso tipo fanno combo
            ROSSO,
            AZZURRO,
            BLU,
            GIALLO,
            BIANCO,
            VERDE,
        }
    }

    Class Obiettivo {
    }

    Class ObiettivoPrivato extends Obiettivo {
    }

    Class ObiettivoComune extends Obiettivo {
    }

    public static void main() {}
}
