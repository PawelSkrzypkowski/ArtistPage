package pl.pawelskrzypkowski.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Paweł Skrzypkowski
 * Wojskowa Akademia Techniczna im. Jarosława Dąbrowskiego, Warszawa 2018.
 */

@Data
@AllArgsConstructor
public class ClassWrapper<K> {
    K object;
}
