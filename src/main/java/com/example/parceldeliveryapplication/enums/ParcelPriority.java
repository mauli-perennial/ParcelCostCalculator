package com.example.parceldeliveryapplication.enums;

import com.example.parceldeliveryapplication.helper.ParcelHelper;

/**
 * enum is for the deciding the priority of the parcel.
 */
public enum ParcelPriority {
    FIRST {
        @Override
        public String getPriority(ParcelHelper parcelDto) {
            if ((parcelDto.getWeight() > parcelDto.getRejectParcel()) || parcelDto.getVolume() == 0 || parcelDto.getWeight() == 0) {
                return ParcelPriority.FIRST.toString();
            } else {
                return "";
            }

        }
    }, SECOND {
        @Override
        public String getPriority(ParcelHelper parcelDto) {
            if (parcelDto.getWeight() > parcelDto.getHeavyParcel()) {
                return ParcelPriority.SECOND.toString();
            } else {
                return "";
            }

        }
    }, THIRD {
        @Override
        public String getPriority(ParcelHelper parcelDto) {
            if (parcelDto.getVolume() < parcelDto.getSmallParcel()) {
                return ParcelPriority.THIRD.toString();
            } else {
                return "";
            }
        }
    }, FOURTH {
        @Override
        public String getPriority(ParcelHelper parcelDto) {
            if (parcelDto.getVolume() >= parcelDto.getHeavyParcel() && parcelDto.getVolume() < parcelDto.getMediumParcel()) {
                return ParcelPriority.FOURTH.toString();
            } else {
                return "";
            }
        }
    }, FIFTH {
        @Override
        public String getPriority(ParcelHelper parcelDto) {
            if (parcelDto.getVolume() >= parcelDto.getMediumParcel()) {
                return ParcelPriority.FIFTH.toString();
            } else {
                return "";
            }
        }
    };

    public abstract String getPriority(ParcelHelper parcelDto);

}
