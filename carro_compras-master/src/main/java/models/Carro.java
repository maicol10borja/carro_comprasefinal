package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    private List<ItemCarro> items;
    public Carro() {
        this.items=new ArrayList<>();
    }
//Validamos que se pueda agregar una sola linea
    public void addItemCarro(ItemCarro itemCarro) {
        if (items.contains(itemCarro)){
            Optional<ItemCarro> optionalItemCarro=items.stream()
                    .filter(i->i.equals(itemCarro))
                    .findAny();
            if (optionalItemCarro.isPresent()){
                ItemCarro i=optionalItemCarro.get();
                i.setCantidad(i.getCantidad()+1);
            }
        }else{
            this.items.add(itemCarro);

        }
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public double getTotal(){
        return items.stream().mapToDouble(ItemCarro::getSbtotal).sum();
    }
}
