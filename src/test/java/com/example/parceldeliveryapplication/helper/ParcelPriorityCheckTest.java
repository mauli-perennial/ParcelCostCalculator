package com.example.parceldeliveryapplication.helper;

import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.enums.ParcelPriority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ParcelPriorityCheckTest {
    @InjectMocks
    ParcelPriorityCheck parcelPriorityCheck;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(parcelPriorityCheck,"rejectParcel",50.0);
        ReflectionTestUtils.setField(parcelPriorityCheck,"heavyParcel",10.0);
        ReflectionTestUtils.setField(parcelPriorityCheck,"smallParcel",1500.0);
        ReflectionTestUtils.setField(parcelPriorityCheck,"mediumParcel",2500.0);
    }

    @Test
    void parcelPriority() {
        ParcelDTO parcelDTO = new ParcelDTO(10.0D,10.0D, 10.0D,10.0D);
        ParcelPriority priority = ParcelPriority.THIRD;
        try (MockedStatic<ParcelPriorityCheck> mockedStatic = Mockito.mockStatic(ParcelPriorityCheck.class)) {
            mockedStatic.when(() -> ParcelPriorityCheck.parcelVolume(parcelDTO)).thenReturn(1000.0D);
        }
        assertEquals(priority,parcelPriorityCheck.parcelPriority(parcelDTO));
    }
}