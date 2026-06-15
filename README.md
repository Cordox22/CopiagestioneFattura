# Copia Gestione Fattura 📋

> Sistema di gestione fatture e clienti in Java

Un'applicazione Java robusta per la gestione completa di fatture, clienti e relative operazioni amministrative.

## 🎯 Caratteristiche

- ✅ **Gestione Clienti**: Aggiungi, modifica, elimina e ricerca clienti
- ✅ **Gestione Fatture**: Creazione, modifica e archiviazione fatture
- ✅ **Calcoli Automatici**: Calcolo IVA, ritenute d'acconto e importi
- ✅ **Persistenza Dati**: Salvataggio in CSV e formato binario (serializzazione)
- ✅ **Ordinamento Avanzato**: Ordina per cognome, comune, data, numero fattura
- ✅ **Ricerca Flessibile**: Ricerca per codice fiscale, nome e cognome, anno
- ✅ **Interfaccia Interattiva**: Menu testuale intuitivo

## 📋 Requisiti

- Java 11 o superiore
- Maven 3.6+

## 🚀 Quick Start

### Clone e Setup

```bash
git clone https://github.com/Cordox22/CopiagestioneFattura.git
cd CopiagestioneFattura
mvn clean install
```

### Esecuzione

```bash
# Esecuzione diretta
mvn exec:java -Dexec.mainClass="com.mycompany.copiagestionefattura.CopiaGestioneFattura"

# O tramite JAR compilato
mvn clean package
java -jar target/copiagestionefattura-1.0.0.jar
```

## 📁 Struttura del Progetto

```
CopiagestioneFattura/
├── pom.xml                              # Configurazione Maven
├── .gitignore                           # Git ignore patterns
├── README.md                            # Questo file
└── src/
    ├── main/
    │   ├── java/com/mycompany/copiagestionefattura/
    │   │   ├── CopiaGestioneFattura.java          # Main - Menu principale
    │   │   ├── ArchivioClienti.java               # Gestione clienti
    │   │   ├── ArchivioFatture.java               # Gestione fatture
    │   │   ├── Cliente.java                       # Modello Cliente
    │   │   ├── Fattura.java                       # Modello Fattura
    │   │   ├── RigaPrestazione.java               # Modello dettaglio fattura
    │   │   ├── CsvArchivioClienti.java            # I/O CSV Clienti
    │   │   ├── CsvArchivioFatture.java            # I/O CSV Fatture
    │   │   ├── SerializzazioneArchivioClienti.java # Serializzazione clienti
    │   │   ├── SerializzazioneArchivioFatture.java # Serializzazione fatture
    │   │   ├── IteratoreCliente.java              # Iterator per clienti
    │   │   ├── IteratoreFatture.java              # Iterator per fatture
    │   │   ├── StampanteFattura.java              # Stampa formattata fatture
    │   │   ├── OrdinamentoComune.java             # Comparator per comune
    │   │   ├── OrdinamentoPerData.java            # Comparator per data
    │   │   ├── DatiInvalidiException.java         # Custom exception
    │   │   ├── FatturaNonTrovataException.java    # Custom exception
    │   │   └── NumeroFatturaInvalidoException.java # Custom exception
    │   └── resources/
    │       └── (data files - CSV)
    └── test/
        └── java/com/mycompany/copiagestionefattura/
            └── (test classes)
```

## 🎮 Menu Principale

```
1. Aggiungi un cliente nuovo
2. Emetti fattura
3. Cercare il codice fiscale di un cliente tramite nome e cognome
4. Elenco clienti ordinati per cognome e nome
5. Elenco clienti ordinati per comune
6. Elenco fatture ordinate per data
7. Stampa tutte le fatture collegate ad un cliente
8. Modifica i dati di un cliente
9. Elimina un cliente
10. Stampa di tutti i clienti
11. Stampa di tutte le fatture
12. Cerca fatture per anno
13. Modifica righe della fattura non ancora emessa
14. Esci
```

## 📊 Modelli Dati

### Cliente
- Nome, Cognome
- Indirizzo, Civico, CAP, Comune
- Telefono
- Partita IVA (opzionale)
- Codice Fiscale (chiave primaria)

### Fattura
- Numero fattura (auto-incrementante)
- Data emissione
- Codice Fiscale cliente (riferimento)
- Lista righe prestazioni
- Calcoli: Imponibile, IVA, Totale, Ritenuta d'acconto (opzionale)
- Note (es. emissione tardiva)

### RigaPrestazione
- Descrizione prestazione
- Importo netto

## 💾 Persistenza Dati

### Formati Supportati

1. **CSV** - Per export/import facilmente leggibili
   - `clienti.csv`: Nome, Cognome, Indirizzo, Civico, CAP, Comune, Telefono, P.IVA, Codice Fiscale
   - `fatture.csv`: Numero, Data, Cliente CF, Dettagli calcoli

2. **Serializzazione Binaria** - Per backup veloci
   - `clienti.ser`: Archivio clienti serializzato
   - `fatture.ser`: Archivio fatture serializzato

3. **Backup Automatici**
   - `clienti.bak`: Backup corrente
   - `clienti.bak2`: Backup precedente

## 🔧 Validazione Dati

Il sistema applica validazioni con espressioni regolari:

| Campo | Formato | Esempio | Regex |
|-------|---------|---------|-------|
| Nome/Cognome | Solo lettere e spazi | Mario Rossi | `[A-Za-zÀ-ÖØ-öø-ÿ ]+` |
| Civico | Numeri, lettere, slash | 12A, 5/3 | `[0-9A-Za-z/]+` |
| CAP | 5 cifre | 06123 | `[0-9]{5}` |
| Telefono | 6-15 cifre | 3471234567 | `[0-9]{6,15}` |
| Partita IVA | 11 cifre | 01234567890 | `[0-9]{11}` |
| Codice Fiscale | 16 alfanumerici | RSSMRA80A01H501U | `[A-Za-z0-9]{16}` |

## 🧮 Calcoli Fattura

### Per Artigiani (default)
- **Imponibile**: Somma righe prestazioni
- **IVA**: Imponibile × 22%
- **Totale Ivato**: Imponibile + IVA

### Per Professionisti (con flag)
- **IVA**: Imponibile × 22%
- **Totale Ivato**: Imponibile + IVA
- **Ritenuta**: Imponibile × 20%
- **Netto a Pagare**: Totale Ivato - Ritenuta

## 🎯 Design Patterns Implementati

- **Iterator Pattern**: `IteratoreCliente`, `IteratoreFatture`
- **Comparator Pattern**: `OrdinamentoComune`, `OrdinamentoPerData`
- **Strategy Pattern**: Calcoli diversi per artigiani vs professionisti
- **Serializable Pattern**: Persistenza oggetti
- **Exception Handling**: Custom exceptions per errori specifici

## 📝 Esempio di Utilizzo

```java
// Creazione archivi
ArchivioClienti clienti = new ArchivioClienti();
ArchivioFatture fatture = new ArchivioFatture(clienti);

// Aggiunta cliente
clienti.aggiungiCliente(
    "Mario", "Rossi", "Via Roma", "13", 
    "06123", "Roma", "3471234567", "01234567890", "RSSMRA80A01H501U"
);

// Creazione fattura
int numFattura = fatture.inserimento("", "RSSMRA80A01H501U");
Fattura f = fatture.getFattura(numFattura);
f.aggiungiRiga("Consulenza", 500.00);
f.calcoloArtigiano();

// Ricerca
Cliente c = clienti.cercaPerCF("RSSMRA80A01H501U");
```

## 🤝 Contribuire

Contributi, segnalazioni di bug e suggerimenti sono benvenuti!

1. Fork il progetto
2. Crea un branch per la tua feature (`git checkout -b feature/AmazingFeature`)
3. Commit le tue modifiche (`git commit -m 'Add some AmazingFeature'`)
4. Push al branch (`git push origin feature/AmazingFeature`)
5. Apri una Pull Request

## 📄 Licenza

Questo progetto è distribuito sotto la licenza MIT. Vedi il file `LICENSE` per dettagli.

## 👤 Autore

- **GitHub**: [@Cordox22](https://github.com/Cordox22)

## 📌 Versioni

### v1.0.0 (Current)
- ✅ Gestione clienti completa
- ✅ Gestione fatture completa
- ✅ Persistenza CSV e binaria
- ✅ Ordinamenti avanzati
- ✅ Validazioni con regex
- ✅ Menu interattivo

### Future Roadmap
- 🔄 Interfaccia grafica (JavaFX)
- 🔄 Database relazionale (JDBC/JPA)
- 🔄 Generazione PDF
- 🔄 API REST
- 🔄 Test automatizzati completi

## 💬 Supporto

Per domande o problemi:
1. Controlla la [documentazione](./README.md)
2. Apri un [Issue](https://github.com/Cordox22/CopiagestioneFattura/issues)
3. Consulta il [Wiki](https://github.com/Cordox22/CopiagestioneFattura/wiki) (se disponibile)

---

⭐ Se il progetto ti è stato utile, considera di mettere una stella!