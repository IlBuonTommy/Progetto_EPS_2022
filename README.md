# Progetto_EPS_2022
>Powered by Tommy, Ale e Paolo

In questa repo trovate un nostro progettino di una rielaborazione di un famoso gioco da tavolo conosciuto da molti come Ludo.

Abbiamo cercato di realizzare una versione multiplayer client-server.

Se volete avere più info le potrete trovare [qui](https://github.com/IlBuonTommy/Progetto_EPS_2022/blob/main/Relazione_Git.pdf)
## Link utili per lo sviluppo delle nuove regole
- [Regole del gioco Ludo](https://it.wikipedia.org/wiki/Ludo_(gioco))
- [Regole del gioco Non t'arrabbiare](https://it.wikipedia.org/wiki/Non_t%27arrabbiare)
# Regole di gioco rielaborate da noi
In maniera casuale viene scelto chi deve fare la prima mossa e gli altri giocano a turno in senso orario.
Il turno di gioco consiste nel lancio del dado e nel conseguente spostamento della pedina di tante caselle quant'è il numero ottenuto. 
Le pedine però possono lasciare la base di partenza solo se con il dado si ottiene un 6; ne consegue che se nessuna pedina è fuori dalla base, con un numero diverso da 6 non si può fare alcuna mossa e il turno passa al giocatore seguente. 
Quando un giocatore ottiene un 6 colloca una sua pedina sulla prima casella del suo percorso _(quella in basso a destra a fianco della freccia disegnata nella sua base di partenza)_ e lancia nuovamente il dado: la pedina entrata in gioco avanza di tante caselle quanti sono i punti ottenuti con il dado nella direzione indicata dalla freccia. 
Scopo del gioco è far compiere a tutte e 4 le proprie pedibe il giro completo della plancia di gioco (sulle 40 caselle del circuito esterno) e farle quindi entrare nelle 4 caselle del proprio colore poste all'interno del circuito. 
Ogni volta che un giocatore ottiene un 6 ha facoltà di scelta fra il far uscire dalla base una pedina e il far avanzare una sua pedina che si trova già sul circuito. **Indipendentemente da questa scelta, dopo il lancio di un 6 si ha sempre diritto ad un altro lancio del dado e ad utilizzare i punti con esso ottenuti.**
### Sovrapposizione
Quando invece una pedina raggiunge una casella occupata da una pedina dello stesso colore, le due pedine si "sovrappongono" momentaneamente: finchè una delle due non si sposta, esse non possono essere sorpassate da altre pedine (avversarie o amiche) e non possono neppure essere rimandate alla base. **Attenzione però!**
- Le pedine sovrapposte possono essere solo 2. Non esistono sovrapposizioni da 3 o 4.
- Non si possono effettuare sovrapposizioni nella casella di spawn ne propria ne altrui. Perciò se la prima casella del percorso è occupata da un propria pedina, con un 6 non si può far entrare nel circuito un altra pedina, ma bisogna utilizzare il punteggio per un'altra mossa e quindi ripetere il lancio del dado.
- Le pedine sovrapposte non possono essere sorpassate in nessun caso, ne da pedine amiche ne da quelle avversarie. Può quindi succedere che al proprio turno un giocatore non possa fare nessuna mossa con il punteggio ottenuto: in questo caso egli non muove e il turno passa al giocatore successivo, senza altre conseguenze.
### Mangiata
Quando muovendo una propria pedina si raggiunge esattamente una casella occupata da una pedina avversaria, quest'ultima viene "mangiata" e rimandata nella sua base di partenza, da dove dovrà poi ricominciare il percorso (sempre con un 6). 
Una pedina che esce dalla base di partenza può mangiare una pedina avversaria che eventualmente si trova nella sua casella d'ingresso al percorso.
### Fine gioco
Le pedine che hanno completato il giro del percorso devono occupare le 4 caselle di arrivo per far terminare il gioco. Queste caselle devono necessariamente essere raggiunte con un tiro preciso del dado. Se, ad esempio, un giocatore ha una pedina sull'ultima casella del suo percorso ed ottiene un 5, non può posizionare la sua pedina nell'ultima delle sue caselle d'arrivo. Se il punto da utilizzare fosse stato un 1 o un 2, la pedina, ovviamente, avrebbe potuto raggiungere solo la prima o la seconda delle sue caselle d'arrivo e attendere lanci successivi per avanzare ulteriormente. 
Naturalmente le pedine nelle caselle d'arrivo sono inattaccabili dai pezzi avversari.

# Tabellone
![Tabellone fatto bene](https://upload.wikimedia.org/wikipedia/commons/9/91/Menschenaergern.svg)

