import { animate, animation, keyframes, style } from '@angular/animations';

export const fadeOutAnimation = animation([

    animate(
      '1s ease-in-out',
      keyframes([
        style({
          opacity: 1,
          offset: 0,
        }),
        style({
          opacity: 0.5,
          offset: 0.3,
        }),
        style({
          opacity: 0,
          offset: 1.0,
        }),
      ])
    ),
  ]);

  // Animazione di ingresso senza rimbalzo
  export const fadeInAnimation = animation([
    animate(
      '1s ease-in-out',
      keyframes([
        style({
          opacity: 0,
          offset: 0,
        }),
        style({
          opacity: 0.5,
          offset: 0.3,
        }),
        style({
          opacity: 1,
          offset: 1.0,
        }),
      ])
    ),
  ]);


