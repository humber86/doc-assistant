/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano
 */

import java.util.HashMap;
import java.util.Iterator;


public class ConstantesAntibioticos {
    
    private static final String AMOXICILINA = "Amoxicilina";
    private static final String CODIGO_AMOXICILINA = "AMX";
    
    private static final String AMPICILINA = "Ampicilina";
    private static final String CODIGO_AMPICILINA = "AMP";
    
    private static final String APRAMICINA = "Apramicina";
    private static final String CODIGO_APRAMICINA = "APM";
    
    private static final String AVILAMICINA = "Avilamicina";
    private static final String CODIGO_AVILAMICINA = "AVM";
    
    private static final String BACITRACINA = "Bacitracina";
    private static final String CODIGO_BACITRACINA = "BCT";
    
    private static final String CEFADROXIL = "Cefadroxil";
    private static final String CODIGO_CEFADROXIL = "CDX";
    
    private static final String CEFALEXINA = "Cefalexina";
    private static final String CODIGO_CEFALEXINA = "CFL";
    
    private static final String CEFOVECIN = "Cefovecin";
    private static final String CODIGO_CEFOVECIN = "CFV";
    
    private static final String CEFRADINA = "Cefradina";
    private static final String CODIGO_CEFRADINA = "CFD";
    
    private static final String CEFTIOFUR = "Ceftiofur";
    private static final String CODIGO_CEFTIOFUR = "CTF";
    
    private static final String CEPHAPIRINA = "Cephapirina";
    private static final String CODIGO_CEPHAPIRINA = "CPP";
    
    private static final String CIPROFLOXACINA = "Ciprofloxacina";
    private static final String CODIGO_CIPROFLOXACINA = "CPF";
    
    private static final String CLORTETRACICLINA = "Clortetraciclina";
    private static final String CODIGO_CLORTETRACICLINA = "CTC";
    
    private static final String COLISTINA = "Colistina";
    private static final String CODIGO_COLISTINA = "CLT";
    
    private static final String DANOFLOXACINA = "Danofloxacina";
    private static final String CODIGO_DANOFLOXACINA = "DNF";
    
    private static final String DEXAMETASONA = "Dexametasona";
    private static final String CODIGO_DEXAMETASONA = "DXM";
    
    private static final String DOXICICLINA = "Doxiciclina";
    private static final String CODIGO_DOXICICLINA = "DXC";
    
    private static final String ENROFLOXACINA = "Enrofloxacina";
    private static final String CODIGO_ENROFLOXACINA = "ERF";
    
    private static final String ERITROMICINA = "Eritromicina";
    private static final String CODIGO_ERITROMICINA = "ETM";
    
    private static final String ESPECTINOMICINA = "Espectinomicina";
    private static final String CODIGO_ESPECTINOMICINA = "EPT";
    
    private static final String ESPIRAMICINA = "Espiramicina";
    private static final String CODIGO_ESPIRAMICINA = "EPM";
    
    private static final String ESTREPTOMICINA = "Estreptomicina";
    private static final String CODIGO_ESTREPTOMICINA = "ETT";
    
    private static final String FLORFENICOL = "Florfenicol";
    private static final String CODIGO_FLORFENICOL = "FFN";
    
    private static final String FLUMEQUINA = "Flumequina";
    private static final String CODIGO_FLUMEQUINA = "FMQ";
    
    private static final String FOSFOMICINA = "Fosfomicina";
    private static final String CODIGO_FOSFOMICINA = "FFM";
    
    private static final String FTALILSULFATIAZOL = "Ftalilsulfatiazol";
    private static final String CODIGO_FTALILSULFATIAZOL = "FSF";
    
    private static final String GENTAMICINA = "Gentamicina";
    private static final String CODIGO_GENTAMICINA = "GTM";
    
    private static final String KANAMICINA = "Kanamicina";
    private static final String CODIGO_KANAMICINA = "KNM";
    
    private static final String LINCOMICINA = "Lincomicina";
    private static final String CODIGO_LINCOMICINA = "LCM";
    
    private static final String MARBOFLOXACINA = "Marbofloxacina";
    private static final String CODIGO_MARBOFLOXACINA = "MBF";
    
    private static final String NEOMICINA = "Neomicina";
    private static final String CODIGO_NEOMICINA = "NEM";
    
    private static final String NORFLOXACINA = "Norfloxacina";
    private static final String CODIGO_NORFLOXACINA = "NFX";
    
    private static final String OXITETRACICLINA = "Oxitetraciclina";
    private static final String CODIGO_OXITETRACICLINA = "OTC";
    
    private static final String PENETAMATO = "Penetamato";
    private static final String CODIGO_PENETAMATO = "PNT";
    
    private static final String PENICILINA = "Penicilina";
    private static final String CODIGO_PENICILINA = "PNC";
    
    private static final String RIFAXIMINA = "Rifaximina";
    private static final String CODIGO_RIFAXIMINA = "RFX";
    
    private static final String SULFADIAZINA = "Sulfadiazina";
    private static final String CODIGO_SULFADIAZINA = "SFD";
    
    private static final String SULFAMETAZINA = "Sulfametazina";
    private static final String CODIGO_SULFAMETAZINA = "SFM";
    
    private static final String TETRACICLINA = "Tetraciclina";
    private static final String CODIGO_TETRACICLINA = "TTC";
    
    private static final String TIAMFENICOL = "Tiamfenicol";
    private static final String CODIGO_TIAMFENICOL = "TFN";
    
    private static final String TIAMULINA = "Tiamulina";
    private static final String CODIGO_TIAMULINA = "TML";
    
    private static final String TILMICOSINA = "Tilmicosina";
    private static final String CODIGO_TILMICOSINA = "TMC";
    
    private static final String TILOSINA = "Tilosina";
    private static final String CODIGO_TILOSINA = "TLS";
    
    private static final String TOBRAMICINA = "Tobramicina";
    private static final String CODIGO_TOBRAMICINA = "TBM";
    
    private static final String TRATOMICINA = "Tratomicina";
    private static final String CODIGO_TRATOMICINA = "TTM";
    
    private static final String TULATRUMICINA = "Tulatrumicina";
    private static final String CODIGO_TULATRUMICINA = "TLM";
    
    
    public static HashMap<String,String> obtenerMapaAntibioticos() {
        
        HashMap<String,String> mapa = new HashMap<>(0);
        mapa.put(AMOXICILINA, CODIGO_AMOXICILINA);
        mapa.put(AMPICILINA, CODIGO_AMPICILINA);
        mapa.put(APRAMICINA, CODIGO_APRAMICINA);
        mapa.put(AVILAMICINA, CODIGO_AVILAMICINA);
        mapa.put(BACITRACINA, CODIGO_BACITRACINA);
        mapa.put(CEFADROXIL, CODIGO_CEFADROXIL);
        mapa.put(CEFALEXINA, CODIGO_CEFALEXINA);
        mapa.put(CEFOVECIN, CODIGO_CEFOVECIN);
        mapa.put(CEFRADINA, CODIGO_CEFRADINA);
        mapa.put(CEFTIOFUR, CODIGO_CEFTIOFUR);
        mapa.put(CEPHAPIRINA, CODIGO_CEPHAPIRINA);
        mapa.put(CIPROFLOXACINA, CODIGO_CIPROFLOXACINA);
        mapa.put(CLORTETRACICLINA, CODIGO_CLORTETRACICLINA);
        mapa.put(COLISTINA, CODIGO_COLISTINA);
        mapa.put(DANOFLOXACINA, CODIGO_DANOFLOXACINA);
        mapa.put(DEXAMETASONA, CODIGO_DEXAMETASONA);
        mapa.put(DOXICICLINA, CODIGO_DOXICICLINA);
        mapa.put(ENROFLOXACINA, CODIGO_ENROFLOXACINA);
        mapa.put(ERITROMICINA, CODIGO_ERITROMICINA);
        mapa.put(ESPECTINOMICINA, CODIGO_ESPECTINOMICINA);
        mapa.put(ESPIRAMICINA, CODIGO_ESPIRAMICINA);
        mapa.put(ESTREPTOMICINA, CODIGO_ESTREPTOMICINA);
        mapa.put(FLORFENICOL, CODIGO_FLORFENICOL);
        mapa.put(FLUMEQUINA, CODIGO_FLUMEQUINA);
        mapa.put(FOSFOMICINA, CODIGO_FOSFOMICINA);
        mapa.put(FTALILSULFATIAZOL, CODIGO_FTALILSULFATIAZOL);
        mapa.put(GENTAMICINA, CODIGO_GENTAMICINA);
        mapa.put(KANAMICINA, CODIGO_KANAMICINA);
        mapa.put(LINCOMICINA, CODIGO_LINCOMICINA);
        mapa.put(MARBOFLOXACINA, CODIGO_MARBOFLOXACINA);
        mapa.put(NEOMICINA, CODIGO_NEOMICINA);
        mapa.put(NORFLOXACINA, CODIGO_NORFLOXACINA);
        mapa.put(OXITETRACICLINA, CODIGO_OXITETRACICLINA);
        mapa.put(PENETAMATO, CODIGO_PENETAMATO);
        mapa.put(PENICILINA, CODIGO_PENICILINA);
        mapa.put(RIFAXIMINA, CODIGO_RIFAXIMINA);
        mapa.put(SULFADIAZINA, CODIGO_SULFADIAZINA);
        mapa.put(SULFAMETAZINA, CODIGO_SULFAMETAZINA);
        mapa.put(TETRACICLINA, CODIGO_TETRACICLINA);
        mapa.put(TIAMFENICOL, CODIGO_TIAMFENICOL);
        mapa.put(TIAMULINA, CODIGO_TIAMULINA);
        mapa.put(TILMICOSINA, CODIGO_TILMICOSINA);
        mapa.put(TILOSINA, CODIGO_TILOSINA);
        mapa.put(TOBRAMICINA, CODIGO_TOBRAMICINA);
        mapa.put(TRATOMICINA, CODIGO_TRATOMICINA);
        mapa.put(TULATRUMICINA, CODIGO_TULATRUMICINA);
        
        return mapa;
    }
    
    
    public static String[] obtenerNombresAntibioticos() {
        
        HashMap<String,String> mapaAntibioticos = obtenerMapaAntibioticos();
        
        Iterator<String> iterador = Utilidades.obtenerIteradorStringOrdenado(mapaAntibioticos);
        
        String[] cadenaAntibioticos = new String[mapaAntibioticos.size()];
        
        for(int i=0 ; iterador.hasNext() ; i++)
        {
            cadenaAntibioticos[i] = iterador.next();
        }
        
	return cadenaAntibioticos;
    }
    
    
    public static String[] obtenerNombresCodigosAntibioticos() {
        
        HashMap<String,String> mapaAntibioticos = obtenerMapaAntibioticos();
        
        Iterator<String> iterador = Utilidades.obtenerIteradorStringOrdenado(mapaAntibioticos);
        
        String[] cadenaAntibioticos = new String[mapaAntibioticos.size()];
        
        for(int i=0 ; iterador.hasNext() ; i++)
        {
            String antibiotico = iterador.next();
            
            cadenaAntibioticos[i] = antibiotico+" - "+mapaAntibioticos.get(antibiotico);
        }
        
	return cadenaAntibioticos;
    }
    
    
}
