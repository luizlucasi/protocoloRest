import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INumeroProtocolo, defaultValue } from 'app/shared/model/numero-protocolo.model';

export const ACTION_TYPES = {
  FETCH_NUMEROPROTOCOLO_LIST: 'numeroProtocolo/FETCH_NUMEROPROTOCOLO_LIST',
  FETCH_NUMEROPROTOCOLO: 'numeroProtocolo/FETCH_NUMEROPROTOCOLO',
  CREATE_NUMEROPROTOCOLO: 'numeroProtocolo/CREATE_NUMEROPROTOCOLO',
  UPDATE_NUMEROPROTOCOLO: 'numeroProtocolo/UPDATE_NUMEROPROTOCOLO',
  DELETE_NUMEROPROTOCOLO: 'numeroProtocolo/DELETE_NUMEROPROTOCOLO',
  RESET: 'numeroProtocolo/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INumeroProtocolo>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type NumeroProtocoloState = Readonly<typeof initialState>;

// Reducer

export default (state: NumeroProtocoloState = initialState, action): NumeroProtocoloState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NUMEROPROTOCOLO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NUMEROPROTOCOLO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_NUMEROPROTOCOLO):
    case REQUEST(ACTION_TYPES.UPDATE_NUMEROPROTOCOLO):
    case REQUEST(ACTION_TYPES.DELETE_NUMEROPROTOCOLO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_NUMEROPROTOCOLO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NUMEROPROTOCOLO):
    case FAILURE(ACTION_TYPES.CREATE_NUMEROPROTOCOLO):
    case FAILURE(ACTION_TYPES.UPDATE_NUMEROPROTOCOLO):
    case FAILURE(ACTION_TYPES.DELETE_NUMEROPROTOCOLO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_NUMEROPROTOCOLO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NUMEROPROTOCOLO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_NUMEROPROTOCOLO):
    case SUCCESS(ACTION_TYPES.UPDATE_NUMEROPROTOCOLO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_NUMEROPROTOCOLO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/numero-protocolos';

// Actions

export const getEntities: ICrudGetAllAction<INumeroProtocolo> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NUMEROPROTOCOLO_LIST,
  payload: axios.get<INumeroProtocolo>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<INumeroProtocolo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NUMEROPROTOCOLO,
    payload: axios.get<INumeroProtocolo>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<INumeroProtocolo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NUMEROPROTOCOLO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INumeroProtocolo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NUMEROPROTOCOLO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<INumeroProtocolo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NUMEROPROTOCOLO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
