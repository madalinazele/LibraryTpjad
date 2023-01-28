package com.library.facade.facet;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FacetValue {
    private String name;
    private int count;
    private boolean selected;
    private String url;
}
