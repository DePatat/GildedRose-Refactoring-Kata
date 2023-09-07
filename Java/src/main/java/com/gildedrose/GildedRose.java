package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    // this is the old function for reference
    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
    // This is the changed version of the previous function
    public void newUpdateQuality() {
        // Updates item.quality and item.sellIn for all the Items according to a certain of rules
        for (int i = 0; i < items.length; i++) {
            Item currItem = items[i];
            if (!isSpecial(currItem)) {
                if(currItem.sellIn < 0){
                    decrQuality(currItem);
                }

                if(!currItem.name.equals("Backstage passes to a TAFKAL80ETC concert")){
                    decrQuality(currItem);
                }

            } else {
                incrQuality(currItem);

                if (currItem.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if(currItem.sellIn < 11){
                        incrQuality(currItem);
                    }
                    if(currItem.sellIn < 6){
                        incrQuality(currItem);
                    }
                    if(currItem.sellIn < 0){
                        resetQuality(currItem);
                    }
                } else if (currItem.name.equals("Aged Brie")){
                    if (currItem.sellIn < 0) {
                        incrQuality(currItem);
                    }
                }
            }

            if (!currItem.name.equals("Sulfuras, Hand of Ragnaros")) {
                decrSellIn(currItem);
            }
        }
    }

    private boolean isSpecial(Item item){
        // Checks if item.name fulfills special criteria
        return item.name.equals("Sulfuras, Hand of Ragnaros") || item.name.equals("Aged Brie");
    }

    private void incrQuality(Item item){
        // Increases item quality by 1 and checks if the quality is higher than 50 for the sake of readability
        if(item.quality < 50){
            item.quality ++;
        }
    }

    private void decrQuality(Item item){
        // Decreases item quality by 1 and checks if the quality is higher than 0 for the sake of readability
        if(item.quality > 0){
            item.quality --;
        }
    }

    private void resetQuality(Item item){
        // Resets item quality
        item.quality = 0;
    }

    private void decrSellIn(Item item) {
        // Decreases item.sellIn by 1
        item.sellIn--;
    }
}