package com.sourcekode.practo.practo.SampleDataProvider;

import com.sourcekode.practo.practo.ModalData.DoctorsData;
import com.sourcekode.practo.practo.ModalData.Specialities;
import com.sourcekode.practo.practo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProvider {
    
    public static List<Specialities> specialities;
    public static Map<String,Specialities> specialitiesMap;
    public static List<DoctorsData> doctorsList;
    public static Map<String,DoctorsData> doctorsMap;

    static {
        specialities = new ArrayList<Specialities>();
        specialitiesMap = new HashMap<String, Specialities>();
        doctorsList = new ArrayList<DoctorsData>();
        doctorsMap = new HashMap<String,DoctorsData>();

        specialities.add(new Specialities("Audiologist", R.drawable.audiologist));
        specialities.add(new Specialities("Allergist",R.drawable.allergist));
        specialities.add(new Specialities("Anesthesiologist",R.drawable.anesthesiologist));
        specialities.add(new Specialities("Cardiologist",R.drawable.cardiologist));
        specialities.add(new Specialities("Dentist",R.drawable.dentist));
        specialities.add(new Specialities("Dermatologist",R.drawable.dermatologist));
        specialities.add(new Specialities("Endocrinologist",R.drawable.endocrinologist));
        specialities.add(new Specialities("Epidemiologist",R.drawable.epidemiology));
        specialities.add(new Specialities("Gynecologist",R.drawable.gynecologist));
        specialities.add(new Specialities("Immunologist",R.drawable.immunologist));
        specialities.add(new Specialities("Infectious Disease Specialist",R.drawable.infection));
        specialities.add(new Specialities("Internal Medicine Specialist",R.drawable.internal_medicine));
        specialities.add(new Specialities("Medical Geneticist",R.drawable.geneticist));
        specialities.add(new Specialities("Microbiologist",R.drawable.microbiologist));
        specialities.add(new Specialities("Neonatologist",R.drawable.neonatologist));
        specialities.add(new Specialities("Neurologist",R.drawable.neurologist));
        specialities.add(new Specialities("Neurosurgeon",R.drawable.neurosurgeon));
        specialities.add(new Specialities("Obstetrician",R.drawable.obstetrician));
        specialities.add(new Specialities("Oncologist",R.drawable.oncologist));
        specialities.add(new Specialities("Orthopedic Surgeon",R.drawable.orthopedic));
        specialities.add(new Specialities("ENT Speialist",R.drawable.ent));
        specialities.add(new Specialities("Pediatrician",R.drawable.pediatician));
        specialities.add(new Specialities("Physiologist",R.drawable.physiologist));
        specialities.add(new Specialities("Plastic Surgeon",R.drawable.plastic_surgeon));
        specialities.add(new Specialities("Podiatrist",R.drawable.podiatrist));
        specialities.add(new Specialities("Psychiatrist",R.drawable.psychiatrist));
        specialities.add(new Specialities("Radiologist",R.drawable.radiologist));
        specialities.add(new Specialities("Rheumatologist",R.drawable.rheumatologist));
        specialities.add(new Specialities("Surgeon",R.drawable.surgeon));
        specialities.add(new Specialities("Urologist",R.drawable.urologist));

        doctorsList.add(new DoctorsData(R.drawable.plastic_surgeon,"DoctorName1","prctise",
                "street","locality","rating val","fees"));
        doctorsList.add(new DoctorsData(R.drawable.plastic_surgeon,"DoctorName2","prctise",
                "street","locality","rating val","fees"));
        doctorsList.add(new DoctorsData(R.drawable.plastic_surgeon,"DoctorName3","prctise",
                "street","locality","rating val","fees"));
        doctorsList.add(new DoctorsData(R.drawable.plastic_surgeon,"DoctorName4","prctise",
                "street","locality","rating val","fees"));
        doctorsList.add(new DoctorsData(R.drawable.plastic_surgeon,"DoctorName5","prctise",
                "street","locality","rating val","fees"));
        doctorsList.add(new DoctorsData(R.drawable.plastic_surgeon,"DoctorName6","prctise",
                "street","locality","rating val","fees"));
        doctorsList.add(new DoctorsData(R.drawable.plastic_surgeon,"DoctorName7","prctise",
                "street","locality","rating val","fees"));
        doctorsList.add(new DoctorsData(R.drawable.plastic_surgeon,"DoctorName8","prctise",
                "street","locality","rating val","fees"));

    }
}
