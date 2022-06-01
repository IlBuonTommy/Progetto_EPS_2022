# Progetto_EPS_2022
Powered by Tommy, Ale e Paolo
## Link utili per lo sviluppo
- [Regole del gioco](https://it.wikipedia.org/wiki/Ludo_(gioco))
- [Regole del gioco serie](https://it.wikipedia.org/wiki/Non_t%27arrabbiare)
- [Bacheca trello](https://trello.com/invite/b/eaNeZaxM/313d2b548018af72a80c75691be17ea0/progetto-eps)
## Repo da dove prendere "spunto"
- [Qui](https://github.com/mrpaulblack/human-dont-get-mad)
- [Qui anche](https://github.com/Creator032/LUDO-GAME)
- [Idem qui](https://github.com/AlexandrosPlessias/Ludo-Griniaris-Game)
- [Idem qui: il ritorno](https://github.com/himanshusandha/JAVA-LUDO)
- [Idee per i bot](https://github.com/yan14171/Ludo-Game-Genetic-Heuristics-AI)
# Regole di gioco
In maniera casuale viene scelto chi deve fare la prima mossa e gli altri giocano a turno in senso orario.
Il turno di gioco consiste nel lancio del dado e nel conseguente spostamento di uno dei piolini di tante caselle quant'è il numero ottenuto. 
I piolini però possono lasciare la base di partenza solo se con il dado si ottiene un 6; ne consegue che se nessun piolino è fuori dalla base, con un numero diverso da 6 non si può fare alcuna mossa e il turno passa al giocatore seguente. 
Quando un giocatore ottiene un 6 colloca un suo piolino sulla prima casella del suo percorso _(quella in basso a destra a fianco della freccia disegnata nella sua base di partenza)_ e lancia nuovamente il dado: il piolino entrato in gioco avanza di tante caselle quanti sono i punti ottenuti con il dado nella direzione indicata dalla freccia. 
Scopo del gioco è far compiere tutti e 4 propri piolini il giro completo del tavoliere (sulle 40 caselle del circuito esterno) e farli quindi entrare nelle 4 caselle del proprio colore poste all'interno del circuito. 
Ogni volta che un giocatore ottiene un 6 ha facoltà di scelta fra il far uscire dalla base un suo piolino e il far avanzare un suo piolino che si trova già sul circuito. **Indipendentemente da questa scelta, dopo il lancio di un 6 si ha sempre diritto ad un altro lancio del dado e ad utilizzare i punti con esso ottenuti.**
### Sovrapposizione
Quando invece una pedina raggiunge una casella occupata da una pedina dello stesso colore, le due pedine si "sovrappongono" momentaneamente: finchè una delle due non si sposta, esse non possono essere sorpassate da altre pedine (avversarie o amiche) e non possono neppure essere rimandate alla base. **Attenzione però!**
- Le pedine sovrapposte possono essere solo 2. Non esistono sovrapposizioni da 3 o 4.
- Non si possono effettuare sovrapposizioni nella propria casella di spawn. Perciò se la prima casella del percorso è occupata da un propria pedina, con un 6 non si può far entrare nel circuito un altra pedina, ma bisogna utilizzare il punteggio per un'altra mossa e quindi ripetere il lancio del dado.
- Le pedine sovrapposte non possono essere sorpassate in nessun caso, ne da pedine amiche ne da quelle avversarie. Può quindi succedere che al proprio turno un giocatore non possa fare nessuna mossa con il punteggio ottenuto: in questo caso egli non muove e il turno passa al giocatore successivo, senza altre conseguenze.
### Mangiata
Quando muovendo un proprio piolino si raggiunge esattamente una casella occupata da un piolino avversario, quest'ultimo viene "mangiato" e rimandato nella sua base di partenza, da dove dovrà poi ricominciare il percorso (sempre con un 6). 
Un piolino che esce dalla base di partenza può colpire un piolino avversario che eventualmente si trovi nella sua casella d'ingresso al percorso.
### Fine gioco
Le pedine che hanno completato il giro del percorso devono occupare le 4 caselle di arrivo per far terminare il gioco. Queste caselle devono necessariamente essere raggiunte con un tiro preciso del dado. Se, ad esempio, un giocatore ha un piolino sull'ultima casella del suo percorso ed ottiene un 5, non può posizionare la sua pedina nell'ultima delle sue caselle d'arrivo. Se il punto da utilizzare fosse stato un 1 o un 2, il piolino, ovviamente, avrebbe potuto raggiungere solo la prima o la seconda delle sue caselle d'arrivo e attendere lanci successivi per avanzare ulteriormente. 
Naturalmente le pedine nelle caselle d'arrivo sono inattaccabili dai pezzi avversari.

# Tabellone
![Tabellone fatto bene](https://upload.wikimedia.org/wikipedia/commons/9/91/Menschenaergern.svg)