package com.example.andrzejdevcom.sdacademyaplikacja.component;

import com.example.andrzejdevcom.sdacademyaplikacja.MainActivity;
import com.example.andrzejdevcom.sdacademyaplikacja.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Katarzyna on 2017-05-27.
 */
//Interfejst service component dedykuje na do jakich klas wstrzykniemy
//module oznacza jakie są to moduły może być jeden, a może być też kilka np:
//(modules = {ServiceModule.class, ServiceContext.class}
@Singleton
@Component(modules = {ServiceModule.class})
public interface ServiceComponent {
    void inject(MainActivity activity);
}
