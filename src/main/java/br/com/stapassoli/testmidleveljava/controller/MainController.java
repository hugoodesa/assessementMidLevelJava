package br.com.stapassoli.testmidleveljava.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessement")
@RequiredArgsConstructor
public class MainController {

    /*
        1. Given an array of Objects, returns another array that contains the same elements of the input array in reverse
        order. The original array must not be changed,

        to run on postman or insomina : http://localhost:9000/assessement/reverse-array?elements=1,2,3
     */

    @GetMapping("/reverse-array")
    public Object[] revertOrder(@RequestParam Object[] elements) {

        Object[] revert = new Object[elements.length];

        for (int i = 0; i < elements.length; i++) {
            revert[elements.length - 1 - i] = elements[i];
        }

        return revert;
    }

    /*
        Same as (1), but the reversal must be done in-place, that is, the returned array must be the same that was passed
        as argument to the method.

        to run on postman or insomina : http://localhost:9000/assessement/place-reverse-array?elements=1,2,3
     */

    @GetMapping("/place-reverse-array")
    public Object[] inPlaceReverseArray(@RequestParam Object[] elements) {

        int end = elements.length - 1;

        for (int i = 0; end > i; i++) {

            Object temp = elements[i];
            elements[i] = elements[end];
            elements[end] = temp;
            i++;
            end--;

        }

        return elements;
    }

    /*
     * Same as (2), but the argument must be a java.util.List
     *
     * to run made a get to : http://localhost:9000/assessement/in-place-reverse-list?elements=1,2,3
     * */

    @GetMapping("/place-reverse-list")
    public List<Object> inPlaceReverseList(@RequestParam List<Object> elements) {
        int end = elements.size() - 1;

        for (int i = 0; end > i; i++) {
            Object temp = elements.get(i);
            elements.set(i, elements.get(end));
            elements.set(end, temp);
            i++;
            end--;
        }

        return elements;
    }

}
