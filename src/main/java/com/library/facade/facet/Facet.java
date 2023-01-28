package com.library.facade.facet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Facet {
    private List<FacetValue> values;
    private String name;
    private boolean isMultiSelect;
}
